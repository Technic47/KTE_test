package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.TicketDTO;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Ticket extends AbstractEntity {
    @NotNull
    @ManyToOne
    private Doctor doctor;
    @NotNull
    @ManyToOne
    private Customer customer;
    @OneToOne
    private TimeSlot timeSlot;

    public Ticket(Doctor doctor, Customer customer, TimeSlot timeSlot) {
        super();
        this.doctor = doctor;
        this.customer = customer;
        this.timeSlot = timeSlot;
    }

    public Ticket(Long id, UUID uuid, Doctor doctor, Customer customer, TimeSlot timeSlot) {
        super(id, uuid);
        this.doctor = doctor;
        this.customer = customer;
        this.timeSlot = timeSlot;
    }

    public Ticket(TicketDTO dto){
        super();
        this.doctor = dto.getDoctor();
        this.customer = dto.getCustomer();
        this.timeSlot = dto.getTimeSlot();
    }

    public Ticket() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(doctor, ticket.doctor) && Objects.equals(customer, ticket.customer) && Objects.equals(timeSlot, ticket.timeSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor, customer, timeSlot);
    }
}
