package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.services.DoctorService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Doctors", description = "The Doctor API")
@RestController
@RequestMapping("/api/users/doctors")
public class DoctorController extends AbstractController<Doctor, DoctorService, DoctorDTO> {

    private final TicketController ticketController;
    protected DoctorController(DoctorService service, TicketController ticketController) {
        super(service);
        this.ticketController = ticketController;
    }

    @Override
    public ResponseEntity<List<DoctorDTO>> index() {
        List<Doctor> index = service.index();
        List<DoctorDTO> dtoList = new ArrayList<>();
        index.forEach(item -> dtoList.add(DoctorDTO.createDoctorDTO(item)));
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<DoctorDTO> create(@RequestBody DoctorDTO newDTO) {
        Doctor saved = service.save(new Doctor(newDTO));
        return ResponseEntity.ok(DoctorDTO.createDoctorDTO(saved));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        Doctor doctor = service.getById(id);
        ticketController.cleanUp(doctor.getTickets());
        return super.delete(id);
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
    public ResponseEntity<List<TicketDTO>> getTickets(@PathVariable Long id) {
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
    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getTicketsById(
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
