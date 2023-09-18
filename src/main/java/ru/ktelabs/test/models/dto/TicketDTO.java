package ru.ktelabs.test.models.dto;

import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.Ticket;

import java.util.UUID;

public class TicketDTO extends AbstractDto {
    private Long id;
    @NotNull
    private UUID uuid;
    @NotNull
    private DoctorDTO doctor;
    @NotNull
    private CustomerDTO customer;
    private TimeSlotDTO timeSlot;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.uuid = ticket.getUuid();
        this.doctor = DoctorDTO.createDoctorDTO(ticket.getDoctor());
        this.customer = CustomerDTO.createCustomerDTO(ticket.getCustomer());
        this.timeSlot = TimeSlotDTO.createTimeSlotDTO(ticket.getTimeSlot());
    }

    public TicketDTO() {
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public TimeSlotDTO getTimeSlot() {
        return timeSlot;
    }
}
