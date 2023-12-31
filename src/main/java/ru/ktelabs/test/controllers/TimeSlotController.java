package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.models.dto.TimeSlotShowDTO;
import ru.ktelabs.test.services.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Controller for TimeSlot API.
 */
@Tag(name = "TimeSlots", description = "The TimeSlot API")
@RestController
@RequestMapping("/api/users/timeSlots")
public class TimeSlotController {
    private final TimeSlotService service;
    private final CabinetService cabinetService;
    private final TicketController ticketController;

    protected TimeSlotController(TimeSlotService service, CabinetService cabinetService, TicketController ticketController) {
        this.service = service;
        this.cabinetService = cabinetService;
        this.ticketController = ticketController;
    }

    @Operation(summary = "Get all entities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Index is ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
    @GetMapping()
    public ResponseEntity<List<TimeSlotDTO>> index() {
        List<TimeSlotDTO> slots = service.indexDTO();
        return ResponseEntity.ok(slots);
    }

    @Operation(summary = "Create a new entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlotDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<TimeSlotDTO> create(@Valid @RequestBody TimeSlotDTO newDTO) {
        Cabinet cabinet = cabinetService.findByNumber(newDTO.getCabinetNumber());
        TimeSlot saved = service.save(new TimeSlot(newDTO, cabinet));
        return ResponseEntity.ok(TimeSlotDTO.createTimeSlotDTO(saved));
    }

    @Operation(summary = "Get entity by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlotShowDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<TimeSlotShowDTO> show(@PathVariable Long id) {
        TimeSlot found = service.getById(id);
        return ResponseEntity.ok(new TimeSlotShowDTO(found));
    }

    @Operation(summary = "Update TimeSlot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlot is updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TimeSlotShowDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "TimeSlot not found",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<TimeSlotShowDTO> update(@PathVariable Long id,
                                                  @RequestBody TimeSlotDTO newItem) {
        int number = newItem.getCabinetNumber();
        Cabinet cabinet = cabinetService.findByNumber(number);
        TimeSlot old = service.getById(id);
        TimeSlot updated = new TimeSlot(newItem, cabinet);

        cabinetService.removeSlot(old.getCabinet(), old);
        updated = service.update(old, updated);
        cabinetService.addSlot(number, updated);

        return ResponseEntity.ok(new TimeSlotShowDTO(updated));
    }

    @Operation(summary = "Delete TimeSlot")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlot is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "TimeSlot not found",
                    content = @Content)})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        TimeSlot timeSlot = service.getById(id);
        Cabinet cabinet = timeSlot.getCabinet();
        Ticket ticket = timeSlot.getTicket();

        if (ticket != null) {
            service.removeTicket(timeSlot);
            ticketController.cleanUp(ticket);
        }
        cabinetService.removeSlot(cabinet, timeSlot);
        boolean delete = service.delete(id);
        return ResponseEntity.ok(delete);
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
            return ResponseEntity.ok(service.generateSlots(year, month - 1, day, periodMinutes, cabinetNumber));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
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
            @RequestParam(name = "cabinetNumber", required = false) Integer cabinet,
            @RequestParam(name = "year") Integer year,
            @RequestParam(name = "month") Integer month,
            @RequestParam(name = "day") Integer day) {
        Calendar date = new GregorianCalendar(year, month - 1, day);
        if (cabinet != null) {
            return ResponseEntity.ok(service.getFreeSlotsForCabinetAndDate(cabinet, date));
        } else return ResponseEntity.ok(service.getAllFreeSlotsForDate(date));
    }
}
