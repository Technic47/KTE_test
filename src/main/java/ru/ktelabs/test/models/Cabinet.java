package ru.ktelabs.test.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Cabinet extends AbstractEntity {
    private int number;
    @OneToMany
    private Set<Ticket> tickets;

    public Cabinet(Long id, int number) {
        super(id);
        this.number = number;
    }

    public Cabinet() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> ticket) {
        this.tickets = ticket;
    }
}
