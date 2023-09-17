package ru.ktelabs.soap.models;

import jakarta.persistence.*;
import ru.ktelabs.soap.ws.TimeslotDTO;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"startTime", "finishTime"})})
public class TimeSlot extends AbstractEntity {
    private Calendar startTime;
    private Calendar finishTime;

    public TimeslotDTO toDTO() throws DatatypeConfigurationException {
        TimeslotDTO dto = new TimeslotDTO();
        XMLGregorianCalendar startXML = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) startTime);
        XMLGregorianCalendar finishXML = DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) finishTime);
        dto.setStartTime(startXML);
        dto.setFinishTime(finishXML);
        return dto;
    }

    public TimeSlot(Calendar startTime, Calendar finishTime) {
        super();
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public TimeSlot(Long id, UUID uuid, Calendar startTime, Calendar finishTime) {
        super(id, uuid);
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public TimeSlot() {
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
}
