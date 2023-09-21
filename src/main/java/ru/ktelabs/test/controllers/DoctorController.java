package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.services.DoctorService;

/**
 * Controller for Doctor API.
 */
@Tag(name = "Doctors", description = "The Doctor API")
@RestController
@RequestMapping("/api/users/doctors")
public class DoctorController extends HumanModelController<Doctor, DoctorService, DoctorDTO> {

    protected DoctorController(DoctorService service, TicketController ticketController) {
        super(service, ticketController);
    }
}
