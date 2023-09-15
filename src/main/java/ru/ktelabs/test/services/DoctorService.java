package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.repositories.DoctorRepository;

import java.util.Calendar;

@Service
public class DoctorService extends HumanModelService<Doctor, DoctorRepository> {
    public DoctorService(DoctorRepository repository) {
        super(repository);
    }

    @Override
    public Doctor update(Doctor old, Doctor newEntity) {
        old.setFirstName(newEntity.getFirstName());
        old.setSecondName(newEntity.getSecondName());
        old.setGivenName(newEntity.getGivenName());
        old.setBirthDate(newEntity.getBirthDate());
        old.setSpeciality(newEntity.getSpeciality());

        old.setUpdated(Calendar.getInstance());
        return save(old);
    }
}
