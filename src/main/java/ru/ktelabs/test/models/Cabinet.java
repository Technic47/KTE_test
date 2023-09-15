package ru.ktelabs.test.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Cabinet extends AbstractEntity {
    private int number;
    @OneToMany
    private Set<Ticket> ticket;

    public Cabinet(Long id, int number) {
        super(id);
        this.number = number;
    }

    public Cabinet() {
    }

    public int getNumber() {
        return number;
    }

    public Set<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(Set<Ticket> ticket) {
        this.ticket = ticket;
    }
}
