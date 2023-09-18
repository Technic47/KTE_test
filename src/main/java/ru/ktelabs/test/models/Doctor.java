package ru.ktelabs.test.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.DoctorDTO;

import java.util.*;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Doctor extends HumanModel {
    @NotNull
    private String speciality;

    public Doctor(String firstName, String secondName, String givenName, Gender gender, Calendar birthDate, String speciality) {
        super(firstName, secondName, givenName, gender, birthDate);
        this.speciality = speciality;
    }

    public Doctor(Long id, UUID uuid, String firstName, String secondName, String givenName, Gender gender, Calendar birthDate, int age, Calendar created, Calendar updated, Set<Ticket> tickets, String speciality) {
        super(id, uuid, firstName, secondName, givenName, gender, birthDate, age, created, updated, tickets);
        this.speciality = speciality;
    }

    public Doctor(DoctorDTO dto) {
        super();
        this.firstName = dto.getFirstName();
        this.secondName = dto.getSecondName();
        this.givenName = dto.getGivenName();
        this.gender = dto.getGender();
        this.birthDate = dto.getBirthDate();
        this.speciality = dto.getSpeciality();
        this.age = new GregorianCalendar().get(Calendar.YEAR) - birthDate.get((Calendar.YEAR));
    }

    @Override
    public AbstractDto createDTO() {
        return DoctorDTO.createDoctorDTO(this);
    }

    public Doctor() {
        super();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
