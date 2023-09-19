package ru.ktelabs.test.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.AbstractDto;
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
//            (cascade = CascadeType.ALL)
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

    @Override
    public AbstractDto createDTO() {
        return TimeSlotDTO.createTimeSlotDTO(this);
    }

    public TimeSlot() {
    }

    public TimeSlot(TimeSlotDTO dto, Cabinet cabinet) {
        super();
        this.startTime = dto.getStartTime();
        this.finishTime = dto.getFinishTime();
        this.cabinet = cabinet;
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
        this.duration = Math.abs(finishTime.get(Calendar.MINUTE) - startTime.get(Calendar.MINUTE));
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
        if (!super.equals(o)) return false;
        TimeSlot timeSlot = (TimeSlot) o;
        return Objects.equals(startTime, timeSlot.startTime) && Objects.equals(finishTime, timeSlot.finishTime) && Objects.equals(cabinet, timeSlot.cabinet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startTime, finishTime, cabinet);
    }
}
