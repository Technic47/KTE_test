package ru.ktelabs.test.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import ru.ktelabs.test.models.builders.ArchiveTicketBuilder;

import java.util.Calendar;
import java.util.UUID;

/**
 * Entity for saving data from tickets before their removal.
 */
@Entity
public class ArchiveTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long oldId;
    private UUID uuid;
    private Long doctorId;
    private String doctorCredentials;
    private Long customerId;
    private String customerCredentials;
    private Long timeSlotId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Calendar finishTime;
    private int cabinetNumber;

    public ArchiveTicket() {
    }

    public ArchiveTicket(Long oldId, UUID uuid, Long doctorId, String doctorCredentials, Long customerId, String customerCredentials, Long timeSlotId, Calendar startTime, Calendar finishTime, int cabinetNumber) {
        this.oldId = oldId;
        this.uuid = uuid;
        this.doctorId = doctorId;
        this.doctorCredentials = doctorCredentials;
        this.customerId = customerId;
        this.customerCredentials = customerCredentials;
        this.timeSlotId = timeSlotId;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.cabinetNumber = cabinetNumber;
    }

    public static ArchiveTicket createArchiveTicket(Ticket ticket) {
        return new ArchiveTicketBuilder()
                .setOldId(ticket.getId())
                .setUuid(ticket.getUuid())
                .setDoctorId(ticket.getDoctor().getId())
                .setDoctorCredentials(ticket.getDoctor().getCredentials())
                .setCustomerId(ticket.getCustomer().getId())
                .setCustomerCredentials(ticket.getCustomer().getCredentials())
                .setTimeSlotId(ticket.getTimeSlot().getId())
                .setStartTime(ticket.getTimeSlot().getStartTime())
                .setFinishTime(ticket.getTimeSlot().getFinishTime())
                .setCabinetNumber(ticket.getTimeSlot().getCabinet().getNumber())
                .createArchiveTicket();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOldId() {
        return oldId;
    }

    public void setOldId(Long oldId) {
        this.oldId = oldId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorCredentials() {
        return doctorCredentials;
    }

    public void setDoctorCredentials(String doctorCredentials) {
        this.doctorCredentials = doctorCredentials;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerCredentials() {
        return customerCredentials;
    }

    public void setCustomerCredentials(String customerCredentials) {
        this.customerCredentials = customerCredentials;
    }

    public Long getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Long timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Calendar finishTime) {
        this.finishTime = finishTime;
    }

    public int getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }
}
