package ru.ktelabs.test.models;

import java.util.Set;

public interface TicketHandler {
    Set<Ticket> getTickets();

    void setTickets(Set<Ticket> tickets);
}
