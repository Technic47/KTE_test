package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
public class Customer extends HumanModel implements TicketHandler{
    public Customer(String firstName, String secondName, String givenName, Calendar birthDate) {
        super(firstName, secondName, givenName, birthDate);
    }

    public Customer(Long id, UUID uuid, String firstName, String secondName, String givenName, Calendar birthDate, int age, Calendar created, Calendar updated, Set<Ticket> tickets) {
        super(id, uuid, firstName, secondName, givenName, birthDate, age, created, updated, tickets);
    }

    public Customer() {
        super();
    }
}
