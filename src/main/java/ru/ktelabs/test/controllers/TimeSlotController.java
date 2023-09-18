package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.models.dto.TimeSlotShowDTO;
import ru.ktelabs.test.services.TimeSlotService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;


@Tag(name = "TimeSlots", description = "The TimeSlot API")
@RestController
@RequestMapping("/api/users/timeSlots")
public class TimeSlotController extends AbstractController<TimeSlot, TimeSlotService, TimeSlotDTO> {
    protected TimeSlotController(TimeSlotService service) {
        super(service);
    }

    @Override
    public ResponseEntity<TimeSlot> create(@RequestBody TimeSlotDTO newDTO) {
        return ResponseEntity.ok(service.save(new TimeSlot(newDTO)));
    }

    @Override
    public ResponseEntity<TimeSlot> show(Long id) {
        TimeSlot found = service.getById(id);
        found.getCabinet().setTimeslots(null);
        return ResponseEntity.ok(found);
    }

    @Override
    public ResponseEntity<List<TimeSlot>> index() {
        List<TimeSlot> slots = service.index();
        slots.forEach(slot -> slot.getCabinet().setTimeslots(null));
        return ResponseEntity.ok(slots);
    }

    @Operation(summary = "Generate slots")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Slots generated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content)})
    @PostMapping("/generate")
    public ResponseEntity<List<TimeSlotShowDTO>> populate(
            @RequestParam(name = "cabinetNumber") Integer cabinetNumber,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day,
            @RequestParam(name = "periodMinutes", required = false) Integer periodMinutes
    ) {
        try {
            return ResponseEntity.ok(service.generateSlots(year, month, day, periodMinutes, cabinetNumber));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Set Ticket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlot.class))}),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @PostMapping("/{slotId}/ticket/{ticketId}")
    public ResponseEntity<TimeSlot> setTicket(@PathVariable Long slotId,
                                              @PathVariable Long ticketId) {
        TimeSlot slot = service.setTicket(slotId, ticketId);
        return ResponseEntity.ok(slot);
    }

    @Operation(summary = "Get free timeSlots for cabinet for specified date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlots loaded",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlotShowDTO.class))}),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content)})
    @GetMapping("/free")
    public ResponseEntity<List<TimeSlotShowDTO>> freeSlots(
            @RequestParam(name = "cabinet", required = false) Integer cabinet,
            @RequestParam(name = "year") Integer year,
            @RequestParam(name = "month") Integer month,
            @RequestParam(name = "day") Integer day) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK, day);
        if (cabinet != null) {
            return ResponseEntity.ok(service.getFreeSlotsForCabinetAndDate(cabinet, date));
        } else return ResponseEntity.ok(service.getAllFreeSlotsForDate(date));

    }
}
