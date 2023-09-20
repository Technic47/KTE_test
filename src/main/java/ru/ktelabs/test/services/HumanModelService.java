package ru.ktelabs.test.services;

import ru.ktelabs.test.models.HumanModel;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.repositories.CommonRepository;

import java.util.*;
import java.util.stream.Collectors;

public abstract class HumanModelService<E extends HumanModel, R extends CommonRepository<E>>
        extends AbstractService<E, R> implements ExtendedService<E> {
    public HumanModelService(R repository) {
        super(repository);
    }

    public E addTicket(Long id, Ticket ticket) {
        E customer = getById(id);
        return addTicket(customer, ticket);
    }

    public E addTicket(UUID uuid, Ticket ticket) {
        E customer = getByUuid(uuid);
        return addTicket(customer, ticket);
    }

    public E removeTicket(Long id, Ticket ticket) {
        E customer = getById(id);
        return removeTicket(customer, ticket);
    }

    public E removeTicket(UUID uuid, Ticket ticket) {
        E customer = getByUuid(uuid);
        return removeTicket(customer, ticket);
    }

    public void updateTicket(E human, Ticket oldTicket, Ticket newTicket) {
        Set<Ticket> tickets = human.getTickets();
        if (tickets.contains(oldTicket)) {
            tickets.remove(oldTicket);
            tickets.add(newTicket);
        } else
            throw new IllegalArgumentException(human.getClass().getSimpleName() + " does`t have ticket with id: " + oldTicket.getId());
    }

    public E addTicket(E human, Ticket ticket) {
        Set<Ticket> tickets = human.getTickets();
        tickets.add(ticket);
        return save(human);
    }

    public E removeTicket(E human, Ticket ticket) {
        human.getTickets().remove(ticket);
        return save(human);
    }

    /**
     * Get all Tickets assigned to specified human.
     *
     * @param id id of the human.
     * @return List of TicketDTO.
     */
    public List<TicketDTO> getAllTickets(Long id) {
        E human = getById(id);
        List<TicketDTO> dtoList = new ArrayList<>();
        human.getTickets().forEach(item -> dtoList.add(new TicketDTO(item)));
        return dtoList;
    }

    /**
     * Get all Tickets assigned to specified human.
     *
     * @param uuid uuid of the human.
     * @return List of TicketDTO.
     */

    public List<TicketDTO> getAllTickets(UUID uuid) {
        E human = getByUuid(uuid);
        List<TicketDTO> dtoList = new ArrayList<>();
        human.getTickets().forEach(item -> dtoList.add(new TicketDTO(item)));
        return dtoList;
    }

    /**
     * Get slots for specified human and date.
     *
     * @param id id of human.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getSlots(Long id, Calendar date) {
        E human = getById(id);

        return getSlotsFromModel(human, date);
    }

    /**
     * Get slots for specified human and date.
     *
     * @param uuid uuid of human.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getSlots(UUID uuid, Calendar date) {
        E human = getByUuid(uuid);

        return getSlotsFromModel(human, date);
    }

    /**
     * Get all slots for specified human.
     *
     * @param id id of human.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getAllSlots(Long id) {
        E human = getById(id);

        return getAllSlotsFromModel(human);
    }

    /**
     * Get all slots for specified human.
     *
     * @param uuid uuid of human.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getAllSlots(UUID uuid) {
        E human = getByUuid(uuid);

        return getAllSlotsFromModel(human);
    }

    private List<TimeSlotDTO> getSlotsFromModel(HumanModel model, Calendar date) {
        Calendar finish = new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        finish.set(Calendar.HOUR, 23);
        finish.set(Calendar.MINUTE, 59);
        List<TimeSlotDTO> dtoList = new ArrayList<>();
        Set<Ticket> tickets = model.getTickets()
                .stream()
                .filter(ticket -> ticket.getTimeSlot().getStartTime().after(date))
                .filter(ticket -> ticket.getTimeSlot().getStartTime().before(finish))
                .collect(Collectors.toSet());
        tickets.forEach(ticket -> dtoList.add(TimeSlotDTO.createTimeSlotDTO(ticket.getTimeSlot())));
        return dtoList;
    }

    private List<TimeSlotDTO> getAllSlotsFromModel(HumanModel model) {
        List<TimeSlotDTO> dtoList = new ArrayList<>();
        Set<Ticket> tickets = model.getTickets();
        tickets.forEach(ticket -> dtoList.add(TimeSlotDTO.createTimeSlotDTO(ticket.getTimeSlot())));
        return dtoList;
    }
}
