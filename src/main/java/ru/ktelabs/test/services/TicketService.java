package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.ArchiveTicket;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.repositories.ArchiveTicketRepository;
import ru.ktelabs.test.repositories.TicketRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService extends AbstractService<Ticket, TicketRepository> {
    private final ArchiveTicketRepository archiveTicketRepository;
    public TicketService(TicketRepository repository, ArchiveTicketRepository archiveTicketRepository) {
        super(repository);
        this.archiveTicketRepository = archiveTicketRepository;
    }

    @Override
    public Ticket update(Ticket old, Ticket newEntity) {
        old.setCustomer(newEntity.getCustomer());
        old.setDoctor(newEntity.getDoctor());
        old.setTimeSlot(newEntity.getTimeSlot());
        return save(old);
    }

    public List<TicketDTO> indexDTO(){
        List<TicketDTO> dtoList = new ArrayList<>();
        repository.findAll().forEach(item -> dtoList.add(new TicketDTO(item)));
        return dtoList;
    }

    public void removeTimeSlot(Ticket ticket){
        archiveTicketRepository.save(ArchiveTicket.createArchiveTicket(ticket));
        ticket.setTimeSlot(null);
        save(ticket);
        repository.delete(ticket);
    }
}
