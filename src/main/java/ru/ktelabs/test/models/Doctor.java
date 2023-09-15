package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Calendar;
import java.util.Set;

@Entity
public class Doctor extends HumanModel {
    private String speciality;
    @OneToMany
    private Set<Ticket> tickets;

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

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
