package ru.ktelabs.test.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.TimeSlotDTO;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Entity
public class TimeSlot extends AbstractEntity {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;
    @ManyToOne
    private Cabinet cabinet;
    @OneToOne
    private Ticket ticket;
    private boolean occupied = false;
    @Transient
    private int duration;

    public TimeSlot(Calendar startTime, Calendar finishTime, Cabinet cabinet) {
        super();
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cabinet = cabinet;
    }

    public TimeSlot(Calendar startTime, Calendar finishTime, Cabinet cabinet, Ticket ticket){
        this(startTime, finishTime, cabinet);
        this.ticket = ticket;
        this.occupied = true;
    }

    public TimeSlot(Long id, UUID uuid, Calendar startTime, Calendar finishTime, Cabinet cabinet, Ticket ticket, boolean occupied, int duration) {
        super(id, uuid);
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cabinet = cabinet;
        this.ticket = ticket;
        this.occupied = occupied;
        this.duration = duration;
    }

    public TimeSlot() {
    }

    public TimeSlot(TimeSlotDTO dto) {
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

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeSlot)) return false;
        TimeSlot slot = (TimeSlot) o;
        return occupied == slot.occupied && duration == slot.duration && Objects.equals(startTime, slot.startTime) && Objects.equals(finishTime, slot.finishTime) && Objects.equals(cabinet, slot.cabinet) && Objects.equals(ticket, slot.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, finishTime, cabinet, ticket, occupied, duration);
    }
}
