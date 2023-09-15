package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.Calendar;
import java.util.UUID;

import static java.util.Calendar.*;

@Entity
public class TimeSlot extends AbstractEntity{
    private Calendar startTime;
    private Calendar finishTime;
//    private int year;
//    private int week;
//    private int day;
    private boolean occupied;
    @Transient
    private int duration;

    public TimeSlot(Calendar startTime, Calendar finishTime) {
        super();
        this.startTime = startTime;
        this.finishTime = finishTime;
//        this.year = startTime.get(YEAR);
//        this.week = startTime.get(WEEK_OF_YEAR);
//        this.day = startTime.get(DAY_OF_WEEK);
    }

    public TimeSlot(Long id, UUID uuid, Calendar startTime, Calendar finishTime, boolean occupied) {
        super(id, uuid);
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.occupied = occupied;
    }

    public TimeSlot() {
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Calendar finishTime) {
        this.finishTime = finishTime;
    }

    public int getDuration() {
        this.duration = finishTime.compareTo(startTime);
        return duration;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
