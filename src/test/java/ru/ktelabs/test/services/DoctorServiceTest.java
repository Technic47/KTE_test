package ru.ktelabs.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.CustomerRepository;
import ru.ktelabs.test.repositories.DoctorRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static ru.ktelabs.test.models.Gender.FEMALE;
import static ru.ktelabs.test.models.Gender.MALE;

@SpringBootTest
class DoctorServiceTest {
    private DoctorService service;
    @MockBean
    private DoctorRepository repository;

    @BeforeEach
    void setUp() {
        service = new DoctorService(repository);
    }

    @Test
    void update() {
        Doctor old = new Doctor("ivan", "ivanov", "ivanovich", FEMALE, new GregorianCalendar(), "neiro");
        Doctor newData = new Doctor("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");

        doReturn(old)
                .when(repository)
                .save(old);

        service.update(old, newData);

        verify(repository).save(any(Doctor.class));

        assertThat(old.getUuid()).isNotNull();
        assertThat(old.getFirstName())
                .isNotNull()
                .isEqualTo("pavel");
        assertThat(old.getSecondName())
                .isNotNull()
                .isEqualTo("kuznetsov");
        assertThat(old.getGivenName())
                .isNotNull()
                .isEqualTo("andreevich");
        assertThat(old.getGender())
                .isNotNull()
                .isEqualTo(MALE);
        assertThat(old.getBirthDate())
                .isNotNull();
        assertThat(old.getAge())
                .isNotNull();
        assertNotNull(old.getUpdated());
        assertThat(old.getSpeciality())
                .isNotNull()
                .isEqualTo("dentist");assertNotNull(old.getUpdated());

    }

    @Test
    void getTicketsById() {
        Long id = 1L;
        Doctor doctor = new Doctor("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Customer customer1 = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Customer customer2 = new Customer("ivan", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Customer customer3 = new Customer("pavel", "ivanov", "andreevich", MALE, new GregorianCalendar());

        Ticket ticket1 = new Ticket(doctor, customer1, new TimeSlot());
        Ticket ticket2 = new Ticket(doctor, customer2, new TimeSlot());
        Ticket ticket3 = new Ticket(doctor, customer3, new TimeSlot());

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        doctor.setTickets(tickets);

        doReturn(Optional.of(doctor))
                .when(repository)
                .findById(id);

        List<Ticket> ticketList = service.getTickets(id);

        verify(repository).findById(id);

        assertThat(ticketList).isNotNull()
                .hasSize(3);
    }

    @Test
    void getTicketsByUuid() {
        UUID uuid = UUID.randomUUID();
        Doctor doctor = new Doctor("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Customer customer1 = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Customer customer2 = new Customer("ivan", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Customer customer3 = new Customer("pavel", "ivanov", "andreevich", MALE, new GregorianCalendar());

        Ticket ticket1 = new Ticket(doctor, customer1, new TimeSlot());
        Ticket ticket2 = new Ticket(doctor, customer2, new TimeSlot());
        Ticket ticket3 = new Ticket(doctor, customer3, new TimeSlot());

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        doctor.setTickets(tickets);

        doReturn(Optional.of(doctor))
                .when(repository)
                .findByUuid(uuid);

        List<Ticket> ticketList = service.getTickets(uuid);

        verify(repository).findByUuid(uuid);

        assertThat(ticketList).isNotNull()
                .hasSize(3);
    }
}