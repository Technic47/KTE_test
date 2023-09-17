package ru.ktelabs.soap.repositories;

import org.springframework.stereotype.Repository;
import ru.ktelabs.soap.models.TimeSlot;

import java.util.Calendar;

@Repository
public interface TimeSlotRepository extends CommonRepository<TimeSlot> {
    boolean existsByStartTimeAndFinishTime(Calendar startTime, Calendar finishTime);
}
