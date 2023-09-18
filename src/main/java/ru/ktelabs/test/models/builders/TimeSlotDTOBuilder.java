package ru.ktelabs.test.models.builders;

import ru.ktelabs.test.models.dto.TimeSlotDTO;

import java.util.Calendar;

public class TimeSlotDTOBuilder {
    private Calendar startTime;
    private Calendar finishTime;
    private int cabinetNumber;

    public TimeSlotDTOBuilder setStartTime(Calendar startTime) {
        this.startTime = startTime;
        return this;
    }

    public TimeSlotDTOBuilder setFinishTime(Calendar finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public TimeSlotDTOBuilder setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
        return this;
    }

    public TimeSlotDTO createTimeSlotDTO() {
        return new TimeSlotDTO(startTime, finishTime, cabinetNumber);
    }
}