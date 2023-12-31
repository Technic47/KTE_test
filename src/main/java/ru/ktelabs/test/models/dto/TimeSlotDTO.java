package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.builders.TimeSlotDTOBuilder;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeSlotDTO extends AbstractDto {
    private Long id;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;
    @Positive
    private int cabinetNumber;

    public TimeSlotDTO(Long id, Calendar startTime, Calendar finishTime, int cabinetNumber) {
        this.id = id;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cabinetNumber = cabinetNumber;
    }

    public TimeSlotDTO() {
    }

    public static TimeSlotDTO createTimeSlotDTO(TimeSlot timeSlot) {
        return new TimeSlotDTOBuilder()
                .setId(timeSlot.getId())
                .setStartTime(timeSlot.getStartTime())
                .setFinishTime(timeSlot.getFinishTime())
                .setCabinetNumber(timeSlot.getCabinet().getNumber())
                .createTimeSlotDTO();
    }

    public Long getId() {
        return id;
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
