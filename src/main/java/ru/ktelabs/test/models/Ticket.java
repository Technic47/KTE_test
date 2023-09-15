package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticket extends AbstractEntity {
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Cabinet cabinet;

    @ManyToOne
    private TimeSlot timeSlot;

    public Ticket(Long id, Doctor doctor, Customer customer, Cabinet cabinet, TimeSlot timeSlot) {
        super(id);
        this.doctor = doctor;
        this.customer = customer;
        this.cabinet = cabinet;
        this.timeSlot = timeSlot;
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
