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
import ru.ktelabs.test.services.TimeSlotService;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;


@Tag(name = "TimeSlots", description = "The TimeSlot API")
@RestController
@RequestMapping("/api/users/timeSlots")
public class TimeSlotController extends AbstractController<TimeSlot, TimeSlotService> {
    protected TimeSlotController(TimeSlotService service) {
        super(service);
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
    public ResponseEntity<List<TimeSlot>> populate(
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day,
            @RequestParam(name = "periodMinutes", required = false) Integer periodMinutes
    ) {
        try {
            return ResponseEntity.ok(service.generateSlots(year, month, day, periodMinutes));
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

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<TimeSlot>> freeSlotsForDoctor(
            @PathVariable Long doctorId,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day) {
        Calendar date = new GregorianCalendar();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, month - 1);
        date.set(Calendar.DAY_OF_WEEK, day);
        return ResponseEntity.ok(service.getFreeSlotsForDoctor(doctorId, date));

    }
}
