package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.TimeSlot;

import java.util.Calendar;
import java.util.UUID;

public class TimeSlotShowDTO extends AbstractDto {
    private Long id;
    private UUID uuid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;
    private int cabinetNumber;
    private boolean occupied;
    private int duration;

    public TimeSlotShowDTO(TimeSlot timeSlot) {
        this.id = timeSlot.getId();
        this.uuid = timeSlot.getUuid();
        this.startTime = timeSlot.getStartTime();
        this.finishTime = timeSlot.getFinishTime();
        this.cabinetNumber = timeSlot.getCabinet().getNumber();
        this.occupied = timeSlot.isOccupied();
        this.duration = timeSlot.getDuration();
    }

    public TimeSlotShowDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public int getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
