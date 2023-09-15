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
    public Customer(Long id, String firstName, String secondName, String givenName, Calendar birthDate) {
        super(id, firstName, secondName, givenName, birthDate);
    }

    public Customer() {
        super();
    }
}
