package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.CabinetDTO;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.services.CabinetService;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Cabinets", description = "The Cabinet API")
@RestController
@RequestMapping("/api/users/cabinets")
public class CabinetController extends AbstractController<Cabinet, CabinetService, CabinetDTO> {
    public CabinetController(CabinetService service) {
        super(service);
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
        Cabinet saved = service.save(new Cabinet(newDTO));
        return ResponseEntity.ok(new CabinetDTO(saved));
    }
}
