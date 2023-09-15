package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.services.TimeSlotService;

@Tag(name = "TimeSlots", description = "The TimeSlot API")
@RestController
@RequestMapping("/api/timeSlots")
public class TimeSlotController extends AbstractController<TimeSlot, TimeSlotService> {
    protected TimeSlotController(TimeSlotService service) {
        super(service);
    }
}
