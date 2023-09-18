package ru.ktelabs.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ktelabs.test.customExceptions.ResourceNotFoundException;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.CabinetRepository;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CabinetServiceTest {
    private CabinetService service;
    @MockBean
    private CabinetRepository repository;

    @BeforeEach
    void setUp() {
        service = new CabinetService(repository);
    }

    @Test
    void update() {
        Cabinet old = new Cabinet(100);
        Cabinet newData = new Cabinet(200);

        doReturn(old)
                .when(repository)
                .save(old);

        service.update(old, newData);

        verify(repository).save(any(Cabinet.class));

        assertEquals(old.getNumber(), 200);
    }

    @Test
    void findByNumber() {
        Cabinet cabinet = new Cabinet(100);
        int number = 100;
        doReturn(Optional.of(cabinet))
                .when(repository)
                .findByNumber(number);

        Cabinet found = service.findByNumber(number);

        verify(repository).findByNumber(number);

        assertEquals(number, found.getNumber());
    }
    @Test
    void findByNumberWrong() {
        int number = 100;
        doReturn(Optional.empty())
                .when(repository)
                .findByNumber(number);

        assertThrows(ResourceNotFoundException.class, () -> service.findByNumber(number));
    }


    @Test
    void setSlots() {
        Cabinet cabinet = new Cabinet();
        TimeSlot slot1 = new TimeSlot(new GregorianCalendar(), new GregorianCalendar(), new Cabinet(100));
        TimeSlot slot2 = new TimeSlot(new GregorianCalendar(), new GregorianCalendar(), new Cabinet(200));
        TimeSlot slot3 = new TimeSlot(new GregorianCalendar(), new GregorianCalendar(), new Cabinet(300));
        TimeSlot slot4 = new TimeSlot(new GregorianCalendar(), new GregorianCalendar(), new Cabinet(400));

        Set<TimeSlot> slots = new HashSet<>();
        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);
        slots.add(slot4);

        doReturn(cabinet)
                .when(repository)
                .save(cabinet);

        service.setSlots(cabinet, slots);

        verify(repository).save(any(Cabinet.class));

        assertThat(cabinet.getTimeslots())
                .isNotNull()
                .isNotEmpty()
                .hasSize(4)
                .contains(slot1, slot2, slot3, slot4);
    }
}