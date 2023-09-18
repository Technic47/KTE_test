package ru.ktelabs.test.services;

import ru.ktelabs.test.models.HumanModel;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.repositories.CommonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public abstract class HumanModelService<E extends HumanModel, R extends CommonRepository<E>>
        extends AbstractService<E, R> {
    public HumanModelService(R repository) {
        super(repository);
    }

    public E addTicket(Long id, Ticket ticket) {
        E customer = getById(id);
        return addTicketToCustomer(customer, ticket);
    }

    public E addTicket(UUID uuid, Ticket ticket) {
        E customer = getByUuid(uuid);
        return addTicketToCustomer(customer, ticket);
    }

    public E removeTicket(Long id, Ticket ticket) {
        E customer = getById(id);
        return removeTicketFromCustomer(customer, ticket);
    }

    public E removeTicket(UUID uuid, Ticket ticket) {
        E customer = getByUuid(uuid);
        return removeTicketFromCustomer(customer, ticket);
    }

    public E addTicketToCustomer(E customer, Ticket ticket) {
        customer.getTickets().add(ticket);
        return save(customer);
    }

    public E removeTicketFromCustomer(E customer, Ticket ticket) {
        customer.getTickets().remove(ticket);
        return save(customer);
    }

    protected List<TimeSlotDTO> getSlotsFromModel(HumanModel model) {
        List<TimeSlotDTO> dtoList = new ArrayList<>();
        Set<Ticket> tickets = model.getTickets();
        tickets.forEach(ticket -> dtoList.add(TimeSlotDTO.createTimeSlotDTO(ticket.getTimeSlot())));
        return dtoList;
    }
}
