package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.services.DoctorService;

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
}
