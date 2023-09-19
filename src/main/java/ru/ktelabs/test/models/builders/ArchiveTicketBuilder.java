package ru.ktelabs.test.models.builders;

import ru.ktelabs.test.models.ArchiveTicket;
import ru.ktelabs.test.models.Ticket;

import java.util.Calendar;
import java.util.UUID;

public class ArchiveTicketBuilder {
    private Long id;
    private Long oldId;
    private UUID uuid;
    private Long doctorId;
    private String doctorCredentials;
    private Long customerId;
    private String customerCredentials;
    private Long timeSlotId;
    private Calendar startTime;
    private Calendar finishTime;
    private int cabinetNumber;

    public ArchiveTicketBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ArchiveTicketBuilder setOldId(Long oldId) {
        this.oldId = oldId;
        return this;
    }

    public ArchiveTicketBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public ArchiveTicketBuilder setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
        return this;
    }

    public ArchiveTicketBuilder setDoctorCredentials(String doctorCredentials) {
        this.doctorCredentials = doctorCredentials;
        return this;
    }

    public ArchiveTicketBuilder setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public ArchiveTicketBuilder setCustomerCredentials(String customerCredentials) {
        this.customerCredentials = customerCredentials;
        return this;
    }

    public ArchiveTicketBuilder setTimeSlotId(Long timeSlotId) {
        this.timeSlotId = timeSlotId;
        return this;
    }

    public ArchiveTicketBuilder setStartTime(Calendar startTime) {
        this.startTime = startTime;
        return this;
    }

    public ArchiveTicketBuilder setFinishTime(Calendar finishTime) {
        this.finishTime = finishTime;
        return this;
    }

    public ArchiveTicketBuilder setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
        return this;
    }

    public ArchiveTicket createArchiveTicket() {
        return new ArchiveTicket(oldId, uuid, doctorId, doctorCredentials, customerId, customerCredentials, timeSlotId, startTime, finishTime, cabinetNumber);
    }
}