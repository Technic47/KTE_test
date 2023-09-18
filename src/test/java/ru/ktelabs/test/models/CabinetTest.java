package ru.ktelabs.test.models;

import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CabinetTest {
    @Test
    void CreationTest(){
        Cabinet cabinet = new Cabinet(100);

        assertNotNull(cabinet.getUuid());
        assertThat(cabinet.getNumber())
                .isNotNull()
                .isEqualTo(100);
    }

    @Test
    void equalTest(){
        Cabinet cabinet1 = new Cabinet(100);
        Cabinet cabinet2 = new Cabinet(200);
        assertNotEquals(cabinet1, cabinet2);
    }

    @Test
    void timeSlotsTest(){
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

        cabinet.setTimeslots(slots);

        assertThat(cabinet.getTimeslots())
                .isNotNull()
                .isNotEmpty()
                .hasSize(4)
                .contains(slot1, slot2, slot3, slot4);
    }
}