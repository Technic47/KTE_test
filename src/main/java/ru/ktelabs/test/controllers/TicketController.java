package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.services.TicketService;

@Tag(name = "Tickets", description = "The Ticket API")
@RestController
@RequestMapping("/api/tickets")
public class TicketController extends AbstractController<Ticket, TicketService> {
    protected TicketController(TicketService service) {
        super(service);
    }
}
