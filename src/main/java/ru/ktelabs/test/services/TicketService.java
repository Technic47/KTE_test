package ru.ktelabs.test.services;

import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.repositories.TicketRepository;

public class TicketService extends AbstractService<Ticket, TicketRepository> {
    public TicketService(TicketRepository repository) {
        super(repository);
    }

    @Override
    public Ticket update(Ticket old, Ticket newEntity) {
        old.setCabinet(newEntity.getCabinet());
        old.setCustomer(newEntity.getCustomer());
        old.setDoctor(newEntity.getDoctor());
        old.setTimeSlot(newEntity.getTimeSlot());
        return save(old);
    }
}
