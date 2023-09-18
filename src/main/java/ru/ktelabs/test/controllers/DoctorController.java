package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.services.DoctorService;

import java.util.List;
import java.util.UUID;

@Tag(name = "Doctors", description = "The Doctor API")
@RestController
@RequestMapping("/api/users/doctors")
public class DoctorController extends AbstractController<Doctor, DoctorService, DoctorDTO> {
    protected DoctorController(DoctorService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Doctor> create(@RequestBody DoctorDTO newDTO) {
        return ResponseEntity.ok(service.save(new Doctor(newDTO)));
    }

    @Operation(summary = "Get Tickets by doctorId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets loaded",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content)})
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTickets(id));
    }

    @Operation(summary = "Get Tickets by uuid/doctorId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tickets loaded",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Doctor not found",
                    content = @Content)})
    @GetMapping("/slots")
    public ResponseEntity<List<Ticket>> getTimeSlots(
            @RequestParam(name = "uuid", required = false) UUID uuid,
            @RequestParam(name = "doctorId", required = false) Long doctorId
    ) {
        if (uuid == null) {
            if (doctorId == null) {
                throw new IllegalArgumentException("Specify at least 1 argument!");
            }
            return ResponseEntity.ok(service.getTickets(doctorId));
        }
        return ResponseEntity.ok(service.getTickets(uuid));
    }
}
