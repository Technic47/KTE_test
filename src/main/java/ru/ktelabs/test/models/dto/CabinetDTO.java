package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CabinetDTO extends AbstractDto{
    @NotNull
    private int number;

    public CabinetDTO(int number) {
        this.number = number;
    }

    public CabinetDTO() {
    }

    public int getNumber() {
        return number;
    }
}
