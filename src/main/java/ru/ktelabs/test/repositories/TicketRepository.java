package ru.ktelabs.test.repositories;

import org.springframework.stereotype.Repository;
import ru.ktelabs.test.models.Ticket;

@Repository
public interface TicketRepository extends CommonRepository<Ticket> {
}
