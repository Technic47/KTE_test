package ru.ktelabs.test.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.CabinetDTO;

import java.util.Set;
import java.util.UUID;

@Entity
public class Cabinet extends AbstractEntity {
    @NotNull
    private int number;
    @OneToMany
    private Set<Ticket> tickets;

    public Cabinet(int number) {
        super();
        this.number = number;
    }

    public Cabinet(Long id, UUID uuid, int number, Set<Ticket> tickets) {
        super(id, uuid);
        this.number = number;
        this.tickets = tickets;
    }

    public Cabinet(CabinetDTO dto){
        super();
        this.number = dto.getNumber();
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
