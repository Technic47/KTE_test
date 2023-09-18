package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Gender;
import ru.ktelabs.test.models.builders.DoctorDTOBuilder;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDTO extends AbstractDto {
    @NotNull
    private String firstName;
    @NotNull
    private String secondName;
    private String givenName;
    @NotNull
    @JsonProperty(value = "gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-DD")
    private Calendar birthDate;
    @NotNull
    private String speciality;

    public DoctorDTO(String firstName, String secondName, String givenName, Gender gender, Calendar birthDate, String speciality) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.speciality = speciality;
    }


    public DoctorDTO() {
    }

    public static DoctorDTO createDoctorDTO(Doctor doctor) {
        return new DoctorDTOBuilder()
                .setFirstName(doctor.getFirstName())
                .setSecondName(doctor.getSecondName())
                .setGivenName(doctor.getGivenName())
                .setGender(doctor.getGender())
                .setBirthDate(doctor.getBirthDate())
                .setSpeciality(doctor.getSpeciality())
                .createDoctorDTO();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getGivenName() {
        return givenName;
    }

    public Gender getGender() {
        return gender;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public String getSpeciality() {
        return speciality;
    }
}
