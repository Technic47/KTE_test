package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.TimeSlot;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketDTO extends AbstractDto {
    @NotNull
    private Doctor doctor;
    @NotNull
    private Customer customer;
    private TimeSlot timeSlot;

    public TicketDTO(Doctor doctor, Customer customer, TimeSlot timeSlot) {
        this.doctor = doctor;
        this.customer = customer;
        this.timeSlot = timeSlot;
    }

    public TicketDTO() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
