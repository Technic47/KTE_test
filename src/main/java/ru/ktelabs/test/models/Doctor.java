package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Calendar;
import java.util.Set;
import java.util.UUID;

@Entity
public class Doctor extends HumanModel {
    private String speciality;

    public Doctor(String firstName, String secondName, String givenName, Calendar birthDate) {
        super(firstName, secondName, givenName, birthDate);
    }

    public Doctor(Long id, UUID uuid, String firstName, String secondName, String givenName, Calendar birthDate, int age, Calendar created, Calendar updated, Set<Ticket> tickets, String speciality) {
        super(id, uuid, firstName, secondName, givenName, birthDate, age, created, updated, tickets);
        this.speciality = speciality;
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
