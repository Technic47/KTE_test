package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.TimeSlotRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.HOUR;

@Service
public class TimeSlotService extends AbstractService<TimeSlot, TimeSlotRepository> {
    private static int WORKING_HOURS_START = 9;
    private static int WORKING_MINUTES_START = 0;
    private static int WORKING_HOURS_FINISH = 18;
    private static int WORKING_MINUTES_FINISH = 0;

    public TimeSlotService(TimeSlotRepository repository) {
        super(repository);
    }

    @Override
    public TimeSlot update(TimeSlot old, TimeSlot newEntity) {
        old.setStartTime(newEntity.getStartTime());
        old.setFinishTime(newEntity.getFinishTime());
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

    public List<TimeSlot> generateSlots(int year, int month, int day, int periodMinutes) {
        List<TimeSlot> slotList = new ArrayList<>();
        int periodHours = periodMinutes / 60;

        Calendar currentPosition = new GregorianCalendar(year, month, day,
                WORKING_HOURS_START, WORKING_MINUTES_START, 0);

        while (currentPosition.get(HOUR) < WORKING_HOURS_FINISH) {
            int newHours = WORKING_HOURS_START + periodHours;
            int newMinutes = WORKING_MINUTES_START + periodMinutes;

            if (newMinutes >= 60) {
                int hours = newMinutes / 60;
                newHours += hours;
                newMinutes -= 60 * hours;
            }

            Calendar finish = new GregorianCalendar(year, month, day, newHours, newMinutes, 0);
            slotList.add(save(new TimeSlot(currentPosition, finish)));
            currentPosition = finish;
        }


        return slotList;
    }
}
