package ru.ktelabs.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.ArchiveTicketRepository;
import ru.ktelabs.test.repositories.TicketRepository;

import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static ru.ktelabs.test.models.Gender.MALE;

@SpringBootTest
class TicketServiceTest {
    private TicketService service;
    @MockBean
    private TicketRepository repository;
    @MockBean
    private ArchiveTicketRepository archiveTicketRepository;

    @BeforeEach
    void setUp() {
        service = new TicketService(repository, archiveTicketRepository);
    }

    @Test
    void update() {
        Doctor doctor = new Doctor();
        Doctor newDoctor = new Doctor("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Customer customer = new Customer();
        Customer newCustomer = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        TimeSlot timeSlot = new TimeSlot();
        Ticket old = new Ticket(doctor, customer, timeSlot);
        Ticket newData = new Ticket(newDoctor, newCustomer, timeSlot);

        doReturn(old)
                .when(repository)
                .save(old);

        service.update(old, newData);

        verify(repository).save(any(Ticket.class));

        assertNotNull(old.getUuid());
        assertThat(old.getCustomer()).isNotNull().isEqualTo(newCustomer);
        assertThat(old.getDoctor()).isNotNull().isEqualTo(newDoctor);
        assertThat(old.getTimeSlot()).isNotNull().isEqualTo(timeSlot);
    }
}