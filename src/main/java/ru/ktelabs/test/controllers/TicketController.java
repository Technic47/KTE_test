package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.models.dto.TicketDTO;
import ru.ktelabs.test.services.TicketService;

@Tag(name = "Tickets", description = "The Ticket API")
@RestController
@RequestMapping("/api/users/tickets")
public class TicketController extends AbstractController<Ticket, TicketService, TicketDTO> {
    protected TicketController(TicketService service) {
        super(service);
    }

    @Override
    public ResponseEntity<Ticket> create(@RequestBody TicketDTO newDTO) {
        return ResponseEntity.ok(service.save(new Ticket(newDTO)));
    }
}
