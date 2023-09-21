package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.dto.CabinetDTO;
import ru.ktelabs.test.services.CabinetService;
import ru.ktelabs.test.services.TimeSlotService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Controller for Cabinet API.
 */
@Tag(name = "Cabinets", description = "The Cabinet API")
@RestController
@RequestMapping("/api/users/cabinets")
public class CabinetController extends AbstractController<Cabinet, CabinetService, CabinetDTO> {
    private final TimeSlotService timeSlotService;

    public CabinetController(CabinetService service, TimeSlotService timeSlotService) {
        super(service);
        this.timeSlotService = timeSlotService;
    }

    @Override
    public ResponseEntity<List<CabinetDTO>> index() {
        List<Cabinet> index = service.index();
        List<CabinetDTO> dtoList = new ArrayList<>();
        index.forEach(item -> dtoList.add(new CabinetDTO(item)));
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<CabinetDTO> create(@RequestBody CabinetDTO newDTO) {
        if (service.existByNumber(newDTO.getNumber())) {
            throw new IllegalArgumentException("Cabinet with number: " + newDTO.getNumber() + " already exists!");
        }
        Cabinet saved = service.save(new Cabinet(newDTO));
        return ResponseEntity.ok(new CabinetDTO(saved));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
        Cabinet cabinet = service.getById(id);
        Set<TimeSlot> timeslots = cabinet.getTimeslots();

        timeSlotService.cleanSlots(timeslots);
        cabinet.getTimeslots().clear();
        service.save(cabinet);
        timeSlotService.deleteManySlots(timeslots);

        return super.delete(id);
    }
}
