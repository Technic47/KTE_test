package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Calendar;
import java.util.Set;

@Entity
public class Doctor extends HumanModel {
    private String speciality;


    public Doctor(Long id, String firstName, String secondName, String givenName, Calendar birthDate) {
        super(id, firstName, secondName, givenName, birthDate);
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
