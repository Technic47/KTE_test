package ru.ktelabs.test.services;

import ru.ktelabs.test.models.HumanModel;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.models.dto.TimeSlotDTO;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public interface ExtendedService<E extends HumanModel> extends CommonService<E>{
    E addTicket(Long id, Ticket ticket);
    E addTicket(UUID uuid, Ticket ticket);
    E removeTicket(Long id, Ticket ticket);
    E removeTicket(UUID uuid, Ticket ticket);
    void updateTicket(E human, Ticket oldTicket, Ticket newTicket);
    E addTicket(E human, Ticket ticket);
    E removeTicket(E human, Ticket ticket);
    List<TicketDTO> getAllTickets(Long id);
    List<TicketDTO> getAllTickets(UUID uuid);
    List<TimeSlotDTO> getSlots(Long id, Calendar date);
    List<TimeSlotDTO> getSlots(UUID uuid, Calendar date);
    List<TimeSlotDTO> getAllSlots(Long id);
    List<TimeSlotDTO> getAllSlots(UUID uuid);
}
