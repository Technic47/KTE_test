package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Customer extends HumanModel{
    @OneToMany
    private Set<Ticket> tickets;

    public Customer(Long id, String firstName, String secondName, String givenName, Calendar birthDate) {
        super(id, firstName, secondName, givenName, birthDate);
    }

    public Customer() {
        super();
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
