package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TimeSlotDTO extends AbstractDto {
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;

    public TimeSlotDTO(Calendar startTime, Calendar finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public TimeSlotDTO() {
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getFinishTime() {
        return finishTime;
    }
}
