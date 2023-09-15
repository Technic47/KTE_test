package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.services.TimeSlotService;

import java.util.List;

@Tag(name = "TimeSlots", description = "The TimeSlot API")
@RestController
@RequestMapping("/api/users/timeSlots")
public class TimeSlotController extends AbstractController<TimeSlot, TimeSlotService> {
    protected TimeSlotController(TimeSlotService service) {
        super(service);
    }

    @PostMapping("/generate")
    public List<TimeSlot> populate() {
        return service.generateSlots(2022, 22, 5, 15);
    }
}
