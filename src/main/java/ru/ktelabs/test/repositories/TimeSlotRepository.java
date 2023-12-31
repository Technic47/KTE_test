package ru.ktelabs.test.repositories;

import org.springframework.stereotype.Repository;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.TimeSlot;

import java.util.Calendar;
import java.util.List;

@Repository
public interface TimeSlotRepository extends CommonRepository<TimeSlot> {
    List<TimeSlot> findByStartTimeAfterAndFinishTimeBefore(Calendar startTime, Calendar finishTime);
    boolean existsByStartTimeAfterAndFinishTimeBefore(Calendar startTime, Calendar finishTime);
    boolean existsByCabinetAndStartTimeAndFinishTime(Cabinet cabinet, Calendar startTime, Calendar finishTime);
    List<TimeSlot> findByCabinetAndOccupiedAndStartTimeAfterAndFinishTimeBefore(Cabinet cabinet, boolean occupied, Calendar start, Calendar finish);
}
