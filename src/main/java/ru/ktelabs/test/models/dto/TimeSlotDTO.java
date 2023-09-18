package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.builders.TimeSlotDTOBuilder;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeSlotDTO extends AbstractDto {
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;

    private int cabinetNumber;

    public TimeSlotDTO(Calendar startTime, Calendar finishTime, int cabinetNumber) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cabinetNumber = cabinetNumber;
    }

    private TimeSlotDTO() {
    }

    public static TimeSlotDTO createTimeSlotDTO(TimeSlot timeSlot) {
        return new TimeSlotDTOBuilder()
                .setStartTime(timeSlot.getStartTime())
                .setFinishTime(timeSlot.getFinishTime())
                .setCabinetNumber(timeSlot.getCabinet().getNumber())
                .createTimeSlotDTO();
    }

    public int getCabinetNumber() {
        return cabinetNumber;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getFinishTime() {
        return finishTime;
    }
}
