package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ktelabs.test.models.*;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.services.CustomerService;
import ru.ktelabs.test.services.DoctorService;
import ru.ktelabs.test.services.TicketService;
import ru.ktelabs.test.services.TimeSlotService;

import java.util.List;

@Tag(name = "Tickets", description = "The Ticket API")
@RestController
@RequestMapping("/api/users/tickets")
public class TicketController{
    private final TicketService service;
    private final DoctorService doctorService;
    private final CustomerService customerService;
    private final TimeSlotService timeSlotService;
    protected TicketController(TicketService service, DoctorService doctorService, CustomerService customerService, TimeSlotService timeSlotService) {
        this.service = service;
        this.doctorService = doctorService;
        this.customerService = customerService;
        this.timeSlotService = timeSlotService;
    }

    @Operation(summary = "Get all tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Index is ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))})})
    @GetMapping()
    public ResponseEntity<List<TicketDTO>> index(){
        return ResponseEntity.ok(service.indexDTO());
    }

    @Operation(summary = "Create a new entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<TicketDTO> create(
            @RequestParam(name = "doctorId") Long doctorId,
            @RequestParam(name = "customerId") Long customerId,
            @RequestParam(name = "timeSlotId") Long timeSlotId
    ) {
        Doctor doctor = doctorService.getById(doctorId);
        Customer customer = customerService.getById(customerId);
        TimeSlot timeSlot = timeSlotService.getById(timeSlotId);

        Ticket saved = service.save(new Ticket(doctor, customer, timeSlot));
        timeSlot.setTicket(saved);
        timeSlotService.save(timeSlot);

        return ResponseEntity.ok(new TicketDTO(saved));
    }

    @Operation(summary = "Get entity by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<TicketDTO> show(@PathVariable Long id){
        Ticket item = service.getById(id);
        return ResponseEntity.ok(new TicketDTO(item));
    }

    @Operation(summary = "Update entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TicketDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<TicketDTO> update(@PathVariable Long id,
                                            @RequestParam(name = "doctorId") Long doctorId,
                                            @RequestParam(name = "customerId") Long customerId,
                                            @RequestParam(name = "timeSlotId") Long timeSlotId){
        Doctor doctor = doctorService.getById(doctorId);
        Customer customer = customerService.getById(customerId);
        TimeSlot timeSlot = timeSlotService.getById(timeSlotId);

        Ticket old = service.getById(id);
        Ticket newItem = new Ticket(doctor, customer, timeSlot);

        Ticket updated = service.update(old, newItem);
        return ResponseEntity.ok(new TicketDTO(updated));
    }

    @Operation(summary = "Delete entity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entity is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
