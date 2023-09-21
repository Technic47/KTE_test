package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ktelabs.test.models.HumanModel;
import ru.ktelabs.test.models.dto.HumanModelDTO;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.services.HumanModelInterface;

import java.util.*;

/**
 * Common controller for Customer and Doctor model`s API.
 *
 * @param <E> HumanModel inheritors.
 * @param <S> service for model E.
 * @param <T> dto class for model E.
 */
public abstract class HumanModelController<
        E extends HumanModel,
        S extends HumanModelInterface<E>,
        T extends HumanModelDTO
        > extends AbstractController<E, S, T> {
    private final TicketController ticketController;

    protected HumanModelController(S service, TicketController ticketController) {
        super(service);
        this.ticketController = ticketController;
    }

    @Override
    public ResponseEntity<List<T>> index() {
        List<E> index = service.index();
        List<T> dtoList = new ArrayList<>();
        index.forEach(item -> dtoList.add((T) item.createDTO()));
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<T> create(@RequestBody T newDTO) {
        E saved = service.save(newDTO.createHuman());
        return ResponseEntity.ok((T) saved.createDTO());
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        E human = service.getById(id);
        ticketController.cleanUp(human.getTickets());
        return super.delete(id);
    }

    @Operation(summary = "Get Tickets by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets loaded",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Human not found",
                    content = @Content)})
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDTO>> getTickets(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllTickets(id));
    }

    @Operation(summary = "Get Tickets by uuid/id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets loaded",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Human not found",
                    content = @Content)})
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getTicketsById(
            @RequestParam(name = "uuid", required = false) UUID uuid,
            @RequestParam(name = "id", required = false) Long id
    ) {
        if (uuid == null) {
            if (id == null) {
                throw new IllegalArgumentException("Specify at least 1 argument!");
            }
            return ResponseEntity.ok(service.getAllTickets(id));
        }
        return ResponseEntity.ok(service.getAllTickets(uuid));
    }

    @Operation(summary = "Get TimeSlots by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlots loaded",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Human not found",
                    content = @Content)})
    @GetMapping("/{id}/slots")
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlots(
            @PathVariable Long id,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day
    ) {
        if (checkDateIsPresent(year, month, day)) {
            Calendar date = new GregorianCalendar(year, month - 1, day);
            return loadSlotsById(id, date);
        } else return loadSlotsById(id, null);
    }

    /**
     * Check if date is present and valid.
     * If not valid - throws IllegalArgumentException.
     *
     * @param year  year
     * @param month month
     * @param day   day
     * @return Is date present or net.
     */
    private boolean checkDateIsPresent(Integer year, Integer month, Integer day) {
        if (year != null) {
            if (month == null || day == null) {
                throw new IllegalArgumentException("Fill date params properly! year + month + day");
            } else {
                return true;
            }
        } else return false;
    }

    @Operation(summary = "Get TimeSlots by uuid/id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlots loaded",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Human not found",
                    content = @Content)})
    @GetMapping("/slots")
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlots(
            @RequestParam(name = "uuid", required = false) UUID uuid,
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "month", required = false) Integer month,
            @RequestParam(name = "day", required = false) Integer day
    ) {
        boolean withDate = checkDateIsPresent(year, month, day);
        if (uuid == null) {
            if (id == null) {
                throw new IllegalArgumentException("Specify at least 1 argument!");
            }
            if (withDate) {
                Calendar date = new GregorianCalendar(year, month - 1, day);
                return loadSlotsById(id, date);
            } else return loadSlotsById(id, null);
        }
        if (withDate) {
            Calendar date = new GregorianCalendar(year, month - 1, day);
            return loadSlotsByUuid(uuid, date);
        } else return loadSlotsByUuid(uuid, null);
    }

    private ResponseEntity<List<TimeSlotDTO>> loadSlotsById(Long id, Calendar date) {
        if (date != null) {
            return ResponseEntity.ok(service.getSlots(id, date));
        } else return ResponseEntity.ok(service.getAllSlots(id));
    }

    private ResponseEntity<List<TimeSlotDTO>> loadSlotsByUuid(UUID uuid, Calendar date) {
        if (date != null) {
            return ResponseEntity.ok(service.getSlots(uuid, date));
        } else return ResponseEntity.ok(service.getAllSlots(uuid));
    }
}
