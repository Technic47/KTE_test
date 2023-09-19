package ru.ktelabs.test.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.ktelabs.test.models.*;
import ru.ktelabs.test.models.dto.TimeSlotShowDTO;
import ru.ktelabs.test.repositories.TimeSlotRepository;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class TimeSlotServiceTest {
    private TimeSlotService service;
    @MockBean
    private ExecutorService executorService;
    @MockBean
    private TicketService ticketService;
    @MockBean
    private CabinetService cabinetService;
    @MockBean
    private TimeSlotRepository repository;

    @BeforeEach
    void setUp() {
        service = new TimeSlotService(repository, executorService, ticketService, cabinetService);
    }

    @Test
    void update() {
        Cabinet cabinet = new Cabinet(100);
        Cabinet newCabinet = new Cabinet(200);
        Calendar start = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar finish = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        TimeSlot old = new TimeSlot(start, finish, cabinet);
        TimeSlot newData = new TimeSlot(start, finish, newCabinet);

        doReturn(old)
                .when(repository)
                .save(old);

        service.update(old, newData);

        verify(repository).save(any(TimeSlot.class));

        assertThat(old.getStartTime()).isNotNull().isEqualTo(start);
        assertThat(old.getFinishTime()).isNotNull().isEqualTo(finish);
        assertThat(old.getCabinet()).isNotNull().isEqualTo(newCabinet);
        assertEquals(old.getDuration(), 30);
        assertFalse(old.isOccupied());
    }

    @Test
    void findAllInPeriod() {
        Cabinet cabinet = new Cabinet(100);
        Calendar calendar1 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar calendar2 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 15);
        Calendar calendar3 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        Calendar calendar4 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 45);
        Calendar calendar5 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 19, 0);

        List<TimeSlot> slotList = new ArrayList<>();
        slotList.add(new TimeSlot(calendar1, calendar2, cabinet));
        slotList.add(new TimeSlot(calendar2, calendar3, cabinet));
        slotList.add(new TimeSlot(calendar3, calendar4, cabinet));
        slotList.add(new TimeSlot(calendar4, calendar5, cabinet));

        Calendar start = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar finish = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 19, 0);

        doReturn(slotList)
                .when(repository)
                .findByStartTimeAfterAndFinishTimeBefore(start, finish);

        List<TimeSlot> allInPeriod = service.findAllInPeriod(start, finish);

        verify(repository).findByStartTimeAfterAndFinishTimeBefore(start, finish);

        assertThat(allInPeriod).isNotNull().hasSize(4);
    }

//    @Test
//    void setTicket() {
//        Long slotId = 1L;
//        Long ticketId = 10L;
//        Doctor doctor = new Doctor();
//        Customer customer = new Customer();
//        TimeSlot timeSlot = new TimeSlot();
//        Ticket ticket = new Ticket(doctor, customer, timeSlot);
//
//        doReturn(ticket)
//                .when(ticketService)
//                .getById(ticketId);
//
//        doReturn(Optional.of(timeSlot))
//                .when(repository)
//                .findById(slotId);
//
//        doReturn(timeSlot)
//                .when(repository)
//                .save(timeSlot);
//
//        service.setTicket(slotId, ticketId);
//
//        verify(ticketService).getById(ticketId);
//        verify(repository).findById(slotId);
//        verify(repository).save(any(TimeSlot.class));
//
//        assertThat(timeSlot.getTicket()).isNotNull().isEqualTo(ticket);
//    }

    @Test
    void getFreeSlotsForCabinetAndDate() {
        int cabinetNumber = 100;
        Cabinet cabinet = new Cabinet(100);
        Calendar calendar1 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar calendar2 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 15);
        Calendar calendar3 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        Calendar calendar4 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 45);
        Calendar calendar5 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 19, 0);

        List<TimeSlot> slotList = new ArrayList<>();
        slotList.add(new TimeSlot(calendar1, calendar2, cabinet));
        slotList.add(new TimeSlot(calendar2, calendar3, cabinet));
        slotList.add(new TimeSlot(calendar3, calendar4, cabinet));
        slotList.add(new TimeSlot(calendar4, calendar5, cabinet));

        doReturn(cabinet)
                .when(cabinetService)
                .findByNumber(cabinetNumber);

        doReturn(slotList)
                .when(repository)
                .findByCabinetAndOccupiedAndStartTimeAfterAndFinishTimeBefore(any(Cabinet.class), any(Boolean.class), any(Calendar.class), any(Calendar.class));

        List<TimeSlotShowDTO> found = service.getFreeSlotsForCabinetAndDate(cabinetNumber, calendar1);

        verify(repository).findByCabinetAndOccupiedAndStartTimeAfterAndFinishTimeBefore(any(Cabinet.class), any(Boolean.class), any(Calendar.class), any(Calendar.class));

        assertThat(found).isNotNull().hasSize(4);
    }

    @Test
    void getAllFreeSlotsForDate() {
        Cabinet cabinet1 = new Cabinet(100);
        Cabinet cabinet2 = new Cabinet(200);
        Calendar calendar1 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar calendar2 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 15);
        Calendar calendar3 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        Calendar calendar4 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 45);
        Calendar calendar5 = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 19, 0);

        TimeSlot slot1 = new TimeSlot(calendar1, calendar2, cabinet1);
        TimeSlot slot2 = new TimeSlot(calendar2, calendar3, cabinet1);
        TimeSlot slot3 = new TimeSlot(calendar3, calendar4, cabinet2);
        TimeSlot slot4 = new TimeSlot(calendar4, calendar5, cabinet2);

        List<TimeSlot> slotList = Arrays.asList(slot1, slot2, slot3, slot4);

//        Set<TimeSlot> set1 = new HashSet<>();
//        set1.add(slot1);
//        set1.add(slot2);
//        Set<TimeSlot> set2 = new HashSet<>();
//        set1.add(slot3);
//        set1.add(slot4);
//
//        cabinet1.setTimeslots(set1);
//        cabinet2.setTimeslots(set2);

        List<Cabinet> cabinets = Arrays.asList(cabinet1, cabinet2);

        doReturn(cabinets)
                .when(cabinetService)
                .index();

        doReturn(slotList)
                .when(repository)
                .findByCabinetAndOccupiedAndStartTimeAfterAndFinishTimeBefore(any(Cabinet.class), any(Boolean.class), any(Calendar.class), any(Calendar.class));

        List<TimeSlotShowDTO> found = service.getAllFreeSlotsForDate(calendar1);

        verify(repository, times(2)).findByCabinetAndOccupiedAndStartTimeAfterAndFinishTimeBefore(any(Cabinet.class), any(Boolean.class), any(Calendar.class), any(Calendar.class));

        assertThat(found).isNotNull().hasSize(8);
    }

    //пока не решил как проверить генерацию.

    @Test
    void generateSlotsDay() throws ExecutionException, InterruptedException {
//        int cabinetNumber = 100;
//        Cabinet cabinet = new Cabinet(100);
//        Integer year;
//        Integer month;
//        Integer day;
//        Integer periodMinutes;
////        int cabinetNumber;
//
//        doReturn(cabinet)
//                .when(cabinetService)
//                .findByNumber(cabinetNumber);
//
//        doReturn(false)
//                .when(repository)
//                .existsByCabinetAndStartTimeAndFinishTime(any(Cabinet.class), any(Calendar.class), any(Calendar.class));
//
//        service.generateSlots(2022, 12, 10, 30, cabinetNumber);
//
//        int size = verify(repository).saveAll(anyCollection()).size();
//        assertEquals(16, size);
    }
}