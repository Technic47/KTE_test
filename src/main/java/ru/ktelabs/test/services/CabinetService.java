package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.repositories.CabinetRepository;

import java.util.UUID;

@Service
public class CabinetService extends AbstractService<Cabinet, CabinetRepository> {
    public CabinetService(CabinetRepository repository) {
        super(repository);
    }

    @Override
    public Cabinet update(Cabinet old, Cabinet newEntity) {
        old.setNumber(newEntity.getNumber());
        old.setTickets(newEntity.getTickets());
        return save(old);
    }

    public void addTicketToCabinet(Long id, Ticket ticket){
        Cabinet cabinet = getById(id);
        addTicketToCabinet(cabinet, ticket);
    }

    public void addTicketToCabinet(UUID uuid, Ticket ticket){
        Cabinet cabinet = getByUuid(uuid);
        addTicketToCabinet(cabinet, ticket);
    }

    public void removeTicket(Long id, Ticket ticket){
        Cabinet cabinet = getById(id);
        delTicketFromCabinet(cabinet, ticket);
    }

    public void removeTicket(UUID uuid, Ticket ticket){
        Cabinet cabinet = getByUuid(uuid);
        delTicketFromCabinet(cabinet, ticket);
    }

    private void addTicketToCabinet(Cabinet cabinet, Ticket ticket){
        cabinet.getTickets().add(ticket);
        this.save(cabinet);
    }

    private void delTicketFromCabinet(Cabinet cabinet, Ticket ticket){
        cabinet.getTickets().remove(ticket);
        this.save(cabinet);
    }
}
