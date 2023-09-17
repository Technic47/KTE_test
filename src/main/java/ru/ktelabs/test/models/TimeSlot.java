package ru.ktelabs.test.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.TimeSlotDTO;

import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"startTime", "finishTime"})})
public class TimeSlot extends AbstractEntity {
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;
    @OneToOne
    private Ticket ticket;
    private boolean occupied;
    @Transient
    private int duration;

    public TimeSlot(Calendar startTime, Calendar finishTime) {
        super();
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public TimeSlot(Long id, UUID uuid, Calendar startTime, Calendar finishTime, Ticket ticket, boolean occupied) {
        super(id, uuid);
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.ticket = ticket;
        this.occupied = occupied;
    }

    public TimeSlot() {
        this.occupied = false;
    }

    public TimeSlot(TimeSlotDTO dto){
        super();
        this.startTime = dto.getStartTime();
        this.finishTime = dto.getFinishTime();
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        this.occupied = true;
    }
}
