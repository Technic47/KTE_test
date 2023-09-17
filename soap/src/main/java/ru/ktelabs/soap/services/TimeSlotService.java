package ru.ktelabs.soap.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.soap.models.TimeSlot;
import ru.ktelabs.soap.repositories.TimeSlotRepository;

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

    public TimeSlotService(TimeSlotRepository repository, ExecutorService executor) {
        super(repository);
        this.executor = executor;
    }

    @Override
    public TimeSlot update(TimeSlot old, TimeSlot newEntity) {
        old.setStartTime(newEntity.getStartTime());
        old.setFinishTime(newEntity.getFinishTime());
        return save(old);
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
     * @return List of TimeSlots.
     */
    public List<TimeSlot> generateSlots(Integer year, Integer month, Integer day, Integer periodMinutes) throws ExecutionException, InterruptedException {
        return this.doGeneration(year, month, day, periodMinutes);
    }

    private List<TimeSlot> doGeneration(Integer year, Integer month, Integer day, Integer periodMinutes) throws ExecutionException, InterruptedException {
        periodMinutes = periodMinutes != null ? periodMinutes : STANDARD_PERIOD;

        if (year != null) {
            if (month != null) {
                if (day != null) {
                    return repository.saveAll(generateForDay(year, month, day, periodMinutes));
                } else return repository.saveAll(generateForMonth(year, month, periodMinutes));
            } else return repository.saveAll(generateForYear(year, periodMinutes));
        } else {
            Calendar now = new GregorianCalendar();
            return generateForDay(now.get(YEAR),
                    now.get(MONTH),
                    now.get(DAY_OF_WEEK),
                    periodMinutes);
        }
    }

    private List<TimeSlot> generateForYear(int year, int periodMinutes) throws ExecutionException, InterruptedException {
        List<TimeSlot> slotList = new ArrayList<>();
        for (int i = 1; i < 12; i++) {

            int finalI = i;
            slotList.addAll(executor.submit(() -> generateForMonth(year, finalI, periodMinutes)).get());
        }
        return slotList;
    }

    private List<TimeSlot> generateForMonth(int year, int month, int periodMinutes) throws ExecutionException, InterruptedException {
        return executor.submit(() -> {
            YearMonth yearMonth = YearMonth.of(year, month);
            int days = yearMonth.lengthOfMonth();
            List<TimeSlot> slotList = new ArrayList<>();

            for (int i = 1; i < days; i++) {
                slotList.addAll(generateForDay(year, month, i, periodMinutes));
            }
            return slotList;
        }).get();
    }

    private List<TimeSlot> generateForDay(int year, int month, int day, int periodMinutes) {
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
            boolean exist = existByStartAndFinish(start, finish);
            if (!exist) {
                slotList.add(new TimeSlot(start, finish));
            }

            currentHours = newHours;
            currentMinutes = newMinutes;
            difference = dayEnd.compareTo(start);
        }
        return slotList;
    }

    public boolean existByStartAndFinish(Calendar start, Calendar finish) {
        return repository.existsByStartTimeAndFinishTime(start, finish);
    }
}
