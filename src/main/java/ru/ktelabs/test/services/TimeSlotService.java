package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.models.dto.TimeSlotShowDTO;
import ru.ktelabs.test.repositories.TimeSlotRepository;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.util.Calendar.*;

@Service
public class TimeSlotService extends AbstractService<TimeSlot, TimeSlotRepository> {
    private static int WORKING_HOURS_START = 9;
    private static int WORKING_MINUTES_START = 0;
    private static int WORKING_HOURS_FINISH = 18;
    private static int WORKING_MINUTES_FINISH = 0;
    private static int STANDARD_PERIOD = 15;
    private final ExecutorService executor;
    protected final TicketService ticketService;
    protected final CabinetService cabinetService;

    public TimeSlotService(TimeSlotRepository repository, ExecutorService executor, TicketService ticketService, CabinetService cabinetService) {
        super(repository);
        this.executor = executor;
        this.ticketService = ticketService;
        this.cabinetService = cabinetService;
    }

    public List<TimeSlotDTO> indexDTO(){
        List<TimeSlotDTO> dtoList = new ArrayList<>();
        repository.findAll().forEach(item -> dtoList.add(TimeSlotDTO.createTimeSlotDTO(item)));
        return dtoList;
    }

    @Override
    public TimeSlot update(TimeSlot old, TimeSlot newEntity) {
        old.setStartTime(newEntity.getStartTime());
        old.setFinishTime(newEntity.getFinishTime());
        old.setCabinet(newEntity.getCabinet());
        return save(old);
    }

    /**
     * Allow to find any entities in calendar range.
     *
     * @param after  date and time after which to find.
     * @param before date and time before which to find.
     * @return result List with TimeSlots.
     */
    public List<TimeSlot> findAllInPeriod(Calendar after, Calendar before) {
        return repository.findByStartTimeAfterAndFinishTimeBefore(after, before);
    }

    /**
     * Create and set new Ticket to TimeSlot and occupy slot.
     *
     * @param id     id of TimeSlot.
     * @param ticket Ticket to set.
     * @return modified TimeSlot.
     */
    public TimeSlot setTicket(Long id, Ticket ticket) {
        TimeSlot slot = getById(id);
        ticket = ticketService.save(ticket);
        return updateTicket(slot, ticket);
    }

    /**
     * Set existing Ticket to TimeSlot and occupy slot.
     *
     * @param id       id of TimeSlot.
     * @param ticketId Ticket id to set.
     * @return modified TimeSlot.
     */
    public TimeSlot setTicket(Long id, Long ticketId) {
        TimeSlot slot = getById(id);
        Ticket ticket = ticketService.getById(ticketId);
        return updateTicket(slot, ticket);
    }

    private TimeSlot updateTicket(TimeSlot timeSlot, Ticket ticket) {
        timeSlot.setTicket(ticket);
        return save(timeSlot);
    }

    /**
     * Get free timeSlots for specified cabinet and date.
     *
     * @param cabinetNumber cabinet number for search.
     * @param date          date for search.
     * @return List of timeSlotsDTO.
     */
    public List<TimeSlotShowDTO> getFreeSlotsForCabinetAndDate(Integer cabinetNumber, Calendar date) {
        Cabinet cabinet = cabinetService.findByNumber(cabinetNumber);

        return getAllSlots(cabinet, date);
    }

    private List<TimeSlotShowDTO> getAllSlots(Cabinet cabinet, Calendar date) {
        Calendar finish = new GregorianCalendar();
        finish.set(YEAR, date.get(YEAR));
        finish.set(MONTH, date.get(MONTH));
        finish.set(DAY_OF_MONTH, date.get(DAY_OF_MONTH));
        finish.set(HOUR, WORKING_HOURS_FINISH + 1);

        List<TimeSlotShowDTO> dtoList = new ArrayList<>();

        List<TimeSlot> slots = repository.findByCabinetAndOccupiedAndStartTimeAfterAndFinishTimeBefore(cabinet, false, date, finish);
        slots.forEach(slot -> dtoList.add(new TimeSlotShowDTO(slot)));
        return dtoList;
    }

    /**
     * Get all free timeSlots for specified date.
     *
     * @param date date for search.
     * @return List of timeSlotsDTO.
     */
    public List<TimeSlotShowDTO> getAllFreeSlotsForDate(Calendar date) {
        List<TimeSlotShowDTO> dtoList = new ArrayList<>();
        cabinetService.index().forEach(cabinet -> dtoList.addAll(getAllSlots(cabinet, date)));
        return dtoList;
    }

    /**
     * Generation strategy:
     * If no year, no month, no day present - generator will produce TimeSlots for current date.
     * If no month, no day present - generator will produce TimeSlots for given year.
     * If no day present - generator will produce TimeSlots for given month.
     * If all is present - generator will produce TimeSlots for given day.
     *
     * @param year          set year for generation whole year.
     * @param month         set month for generation whole month.
     * @param day           set day for generation whole day.
     * @param periodMinutes period for generation.
     * @return List of generated slots.
     */
    public List<TimeSlotShowDTO> generateSlots(Integer year, Integer month, Integer day, Integer periodMinutes, int cabinetNumber) throws ExecutionException, InterruptedException {
        if (year <= 0) {
            throw new IllegalArgumentException("Parameter 'year' is set incorrectly");
        }
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException("Parameter 'month' is set incorrectly");
        }
        if (day <= 0 || day > 31) {
            throw new IllegalArgumentException("Parameter 'day' is set incorrectly");
        }
        if (periodMinutes <= 0) {
            throw new IllegalArgumentException("Parameter 'periodMinutes' is set incorrectly");
        }

        Cabinet cabinet = cabinetService.findByNumber(cabinetNumber);
        List<TimeSlot> timeSlotList = this.doGeneration(year, month, day, periodMinutes, cabinet);
        List<TimeSlotShowDTO> dtoList = new ArrayList<>();
        cabinetService.setSlots(cabinet, timeSlotList);

        timeSlotList.forEach(slot -> dtoList.add(new TimeSlotShowDTO(slot)));
        return dtoList;
    }

    //main logic of TimeSlots generation
    private List<TimeSlot> doGeneration(Integer year, Integer month, Integer day, Integer periodMinutes, Cabinet cabinet) throws ExecutionException, InterruptedException {
        periodMinutes = periodMinutes != null ? periodMinutes : STANDARD_PERIOD;

        if (year != null) {
            if (month != null) {
                if (day != null) {
                    return repository.saveAll(generateForDay(year, month, day, periodMinutes, cabinet));
                } else return repository.saveAll(generateForMonth(year, month, periodMinutes, cabinet));
            } else return repository.saveAll(generateForYear(year, periodMinutes, cabinet));
        } else {
            Calendar now = new GregorianCalendar();
            return generateForDay(now.get(YEAR),
                    now.get(MONTH),
                    now.get(DAY_OF_WEEK),
                    periodMinutes, cabinet);
        }
    }

    private List<TimeSlot> generateForYear(int year, int periodMinutes, Cabinet cabinet) throws ExecutionException, InterruptedException {
        List<TimeSlot> slotList = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            int finalI = i;
            slotList.addAll(executor.submit(() -> generateForMonth(year, finalI, periodMinutes, cabinet)).get());
        }
        return slotList;
    }

    private List<TimeSlot> generateForMonth(int year, int month, int periodMinutes, Cabinet cabinet) throws ExecutionException, InterruptedException {
        return executor.submit(() -> {
            YearMonth yearMonth = YearMonth.of(year, month);
            int days = yearMonth.lengthOfMonth();
            List<TimeSlot> slotList = new ArrayList<>();
            for (int i = 1; i < days; i++) {
                slotList.addAll(generateForDay(year, month, i, periodMinutes, cabinet));
            }
            return slotList;
        }).get();
    }

    //generation logic for 1 day
    private List<TimeSlot> generateForDay(int year, int month, int day, int periodMinutes, Cabinet cabinet) {
        List<TimeSlot> slotList = new ArrayList<>();
        int periodHours = 0;

        if (periodMinutes >= 60) {
            periodHours = periodMinutes / 60;
            periodMinutes -= 60 * periodHours;
        }

        int currentHours = WORKING_HOURS_START;
        int currentMinutes = WORKING_MINUTES_START;

        Calendar start = new GregorianCalendar(year, month, day, WORKING_HOURS_START, WORKING_MINUTES_START, 0);
        Calendar dayEnd = new GregorianCalendar(year, month, day, WORKING_HOURS_FINISH - periodHours, WORKING_MINUTES_FINISH - periodMinutes, 0);
        int difference = dayEnd.compareTo(start);

        while (difference > 0) {
            start = new GregorianCalendar(year, month, day, currentHours, currentMinutes, 0);

            int newHours = currentHours + periodHours;
            int newMinutes = currentMinutes + periodMinutes;

            if (newMinutes >= 60) {
                int over = newMinutes / 60;
                newHours += over;
                newMinutes -= 60 * over;
            }

            Calendar finish = new GregorianCalendar(year, month, day, newHours, newMinutes, 0);
            boolean exist = existByStartAndFinish(cabinet, start, finish);
            if (!exist) {
                slotList.add(new TimeSlot(start, finish, cabinet));
            }

            currentHours = newHours;
            currentMinutes = newMinutes;
            difference = dayEnd.compareTo(start);
        }
        return slotList;
    }

    public boolean existByStartAndFinish(Cabinet cabinet, Calendar start, Calendar finish) {
        return repository.existsByCabinetAndStartTimeAndFinishTime(cabinet, start, finish);
    }
}
