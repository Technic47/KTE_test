package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.dto.TicketDTO;

import java.util.UUID;

@Entity
public class Ticket extends AbstractEntity {
    @NotNull
    @ManyToOne
    private Doctor doctor;
    @NotNull
    @ManyToOne
    private Customer customer;
    @NotNull
    @ManyToOne
    private Cabinet cabinet;
    @OneToOne
    private TimeSlot timeSlot;

    public Ticket(Doctor doctor, Customer customer, Cabinet cabinet, TimeSlot timeSlot) {
        super();
        this.doctor = doctor;
        this.customer = customer;
        this.cabinet = cabinet;
        this.timeSlot = timeSlot;
    }

    public Ticket(Long id, UUID uuid, Doctor doctor, Customer customer, Cabinet cabinet, TimeSlot timeSlot) {
        super(id, uuid);
        this.doctor = doctor;
        this.customer = customer;
        this.cabinet = cabinet;
        this.timeSlot = timeSlot;
    }

    public Ticket(TicketDTO dto){
        super();
        this.doctor = dto.getDoctor();
        this.customer = dto.getCustomer();
        this.cabinet = dto.getCabinet();
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

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
}
