package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import ru.ktelabs.test.models.Gender;

import java.util.Calendar;

public abstract class HumanModelDTO extends AbstractDto implements HumanModelCreator{
    @NotNull
    @Size(max = 100)
    protected String firstName;
    @NotNull
    @Size(max = 100)
    protected String secondName;
    @Size(max = 100)
    protected String givenName;
    @NotNull
    @JsonProperty(value = "gender")
    @Enumerated(EnumType.STRING)
    protected Gender gender;
    @Past
    @JsonFormat(pattern = "yyyy-MM-DD")
    protected Calendar birthDate;
}
