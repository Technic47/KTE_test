package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.customExceptions.ResourceNotFoundException;
import ru.ktelabs.test.models.ArchiveTicket;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.repositories.ArchiveTicketRepository;
import ru.ktelabs.test.repositories.TicketRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service providing methods for Ticket model.
 */
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

    /**
     * Create List of dto entities.
     *
     * @return List of TicketDTO.
     */
    public List<TicketDTO> indexDTO() {
        List<TicketDTO> dtoList = new ArrayList<>();
        repository.findAll().forEach(item -> dtoList.add(new TicketDTO(item)));
        return dtoList;
    }

    /**
     * Create List of archive tickets.
     *
     * @return List of ArchiveTicket.
     */
    public List<ArchiveTicket> indexArchive() {
        return archiveTicketRepository.findAll();
    }

    /**
     * Get ArchiveTicket by it`s own id.
     *
     * @param id id of ArchiveTicket.
     * @return ArchiveTicket or throw ResourceNotFoundException.
     */
    public ArchiveTicket getArchiveById(Long id) {
        Optional<ArchiveTicket> entity = archiveTicketRepository.findById(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return entity.get();
    }

    /**
     * Get ArchiveTicket by it`s removed Ticket id.
     *
     * @param id id of Ticket.
     * @return ArchiveTicket or throw ResourceNotFoundException.
     */
    public ArchiveTicket getArchiveByTicketId(Long id) {
        Optional<ArchiveTicket> entity = archiveTicketRepository.findByOldId(id);
        if (entity.isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        return entity.get();
    }

    /**
     * Save ticket to archive.
     *
     * @param ticket ticket to save.
     */
    public void archive(Ticket ticket) {
        archiveTicketRepository.save(ArchiveTicket.createArchiveTicket(ticket));
    }

    /**
     * Clear timeSlot.
     *
     * @param ticket ticker for cleaning.
     */
    public void removeTimeSlot(Ticket ticket) {
        ticket.setTimeSlot(null);
        save(ticket);
    }
}
