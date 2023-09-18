package ru.ktelabs.test.models;

import org.junit.jupiter.api.Test;
import ru.ktelabs.test.models.dto.TicketDTO;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    @Test
    void creationTest(){
        Doctor doctor = new Doctor();
        Customer customer = new Customer();
        TimeSlot timeSlot = new TimeSlot();
        Ticket ticket = new Ticket(doctor, customer, timeSlot);

        assertNotNull(ticket.getUuid());
        assertThat(ticket.getCustomer()).isNotNull().isEqualTo(customer);
        assertThat(ticket.getDoctor()).isNotNull().isEqualTo(doctor);
        assertThat(ticket.getTimeSlot()).isNotNull().isEqualTo(timeSlot);
    }

//    @Test
//    void creationFromDTOTest(){
//        Doctor doctor = new Doctor();
//        Customer customer = new Customer();
//        TimeSlot timeSlot = new TimeSlot();
//        TicketDTO dto = new TicketDTO(doctor, customer, timeSlot);
//
//        Ticket ticket = new Ticket(dto);
//
//        assertNotNull(ticket.getUuid());
//        assertThat(ticket.getCustomer()).isNotNull().isEqualTo(customer);
//        assertThat(ticket.getDoctor()).isNotNull().isEqualTo(doctor);
//        assertThat(ticket.getTimeSlot()).isNotNull().isEqualTo(timeSlot);
//    }

    @Test
    void equalsTest(){
        Doctor doctor = new Doctor();
        Customer customer = new Customer();
        TimeSlot timeSlot = new TimeSlot();
        Ticket ticket1 = new Ticket(doctor, customer, timeSlot);
        Ticket ticket2 = new Ticket(doctor, customer, new TimeSlot());

        assertNotEquals(ticket1, ticket2);
    }
}