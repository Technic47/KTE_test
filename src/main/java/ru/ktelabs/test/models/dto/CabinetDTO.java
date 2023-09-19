package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.ktelabs.test.models.Cabinet;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CabinetDTO extends AbstractDto {
    @Positive
    private int number;

    public CabinetDTO(int number) {
        this.number = number;
    }

    public CabinetDTO() {
    }

    public CabinetDTO(Cabinet cabinet) {
        this.number = cabinet.getNumber();
    }

    public int getNumber() {
        return number;
    }
}
