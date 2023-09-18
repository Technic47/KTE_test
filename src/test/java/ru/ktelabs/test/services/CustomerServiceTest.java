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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static ru.ktelabs.test.models.Gender.*;

@SpringBootTest
class CustomerServiceTest {
    private CustomerService service;
    @MockBean
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        service = new CustomerService(repository);
    }

    @Test
    void update() {
        Customer old = new Customer("ivan", "ivanov", "ivanovich", FEMALE, new GregorianCalendar());
        Customer newData = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());

        doReturn(old)
                .when(repository)
                .save(old);

        service.update(old, newData);

        verify(repository).save(any(Customer.class));

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
    }

    @Test
    void getSlotsById() {
        Long id = 1L;
        Customer customer = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Doctor doctor1 = new Doctor("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Doctor doctor2 = new Doctor("ivan", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Doctor doctor3 = new Doctor("pavel", "ivanov", "andreevich", MALE, new GregorianCalendar(), "dentist");

        Ticket ticket1 = new Ticket(doctor1, customer, new TimeSlot());
        Ticket ticket2 = new Ticket(doctor2, customer, new TimeSlot());
        Ticket ticket3 = new Ticket(doctor3, customer, new TimeSlot());

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        customer.setTickets(tickets);

        doReturn(Optional.of(customer))
                .when(repository)
                .findById(id);

        List<TimeSlot> slots = service.getSlots(id);

        verify(repository).findById(id);

        assertThat(slots).isNotNull()
                .hasSize(3);
    }

    @Test
    void getSlotsUuid() {
        UUID uuid = UUID.randomUUID();
        Customer customer = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Doctor doctor1 = new Doctor("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Doctor doctor2 = new Doctor("ivan", "kuznetsov", "andreevich", MALE, new GregorianCalendar(), "dentist");
        Doctor doctor3 = new Doctor("pavel", "ivanov", "andreevich", MALE, new GregorianCalendar(), "dentist");

        Ticket ticket1 = new Ticket(doctor1, customer, new TimeSlot());
        Ticket ticket2 = new Ticket(doctor2, customer, new TimeSlot());
        Ticket ticket3 = new Ticket(doctor3, customer, new TimeSlot());

        Set<Ticket> tickets = new HashSet<>();
        tickets.add(ticket1);
        tickets.add(ticket2);
        tickets.add(ticket3);
        customer.setTickets(tickets);

        doReturn(Optional.of(customer))
                .when(repository)
                .findByUuid(uuid);

        List<TimeSlot> slots = service.getSlots(uuid);

        verify(repository).findByUuid(uuid);

        assertThat(slots).isNotNull()
                .hasSize(3);
    }
}