package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.Calendar;

@Entity
public class TimeSlot extends AbstractEntity{
    private Calendar startTime;
    private Calendar finishTime;
    @Transient
    private int duration;

    public TimeSlot(Long id, Calendar startTime, Calendar finishTime) {
        super(id);
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public TimeSlot() {
    }

    public Calendar getStartTime() {
        this.duration = finishTime.compareTo(startTime);
        return startTime;
    }

    public Calendar getFinishTime() {
        return finishTime;
    }

    public int getDuration() {
        return duration;
    }
}
