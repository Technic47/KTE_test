package ru.ktelabs.test.models.builders;

import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Gender;
import ru.ktelabs.test.models.dto.DoctorDTO;

import java.util.Calendar;

public class DoctorDTOBuilder {
    private String firstName;
    private String secondName;
    private String givenName;
    private Gender gender;
    private Calendar birthDate;
    private String speciality;
    private Doctor doctor;

    public DoctorDTOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public DoctorDTOBuilder setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public DoctorDTOBuilder setGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public DoctorDTOBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public DoctorDTOBuilder setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public DoctorDTOBuilder setSpeciality(String speciality) {
        this.speciality = speciality;
        return this;
    }

    public DoctorDTOBuilder setDoctor(Doctor doctor) {
        this.doctor = doctor;
        return this;
    }

    public DoctorDTO createDoctorDTO() {
        return new DoctorDTO(firstName, secondName, givenName, gender, birthDate, speciality);
    }
}