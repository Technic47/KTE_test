package ru.ktelabs.test.models;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TimeSlotTest {
    @Test
    void creationTest() {
        Cabinet cabinet = new Cabinet(100);
        Calendar start = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar finish = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        TimeSlot slot = new TimeSlot(start, finish, cabinet);

        assertThat(slot.getStartTime()).isNotNull().isEqualTo(start);
        assertThat(slot.getFinishTime()).isNotNull().isEqualTo(finish);
        assertThat(slot.getCabinet()).isNotNull().isEqualTo(cabinet);
        assertEquals(slot.getDuration(), 30);
        assertFalse(slot.isOccupied());
    }

    @Test
    void equalsTest(){
        Cabinet cabinet = new Cabinet(100);
        Calendar start = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar finish = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        TimeSlot slot1 = new TimeSlot(start, finish, cabinet);
        TimeSlot slot2 = new TimeSlot(start, finish, cabinet);
        TimeSlot slot3 = new TimeSlot(start, finish, new Cabinet(200));

        assertEquals(slot1, slot2);
        assertNotEquals(slot1, slot3);
    }

    @Test
    void ticketTest(){
        TimeSlot slot = new TimeSlot();
        Ticket newTicket = new Ticket();
        slot.setTicket(newTicket);

        assertThat(slot.getTicket()).isNotNull().isEqualTo(newTicket);
        assertTrue(slot.isOccupied());
    }
}