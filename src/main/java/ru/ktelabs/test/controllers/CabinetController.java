package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.services.CabinetService;

@Tag(name = "Cabinets", description = "The Cabinet API")
@RestController
@RequestMapping("/api/cabinets")
public class CabinetController extends AbstractController<Cabinet, CabinetService> {
    public CabinetController(CabinetService service) {
        super(service);
    }
}
