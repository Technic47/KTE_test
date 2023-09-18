package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.DoctorRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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

    public List<Ticket> getTickets(Long id) {
        Doctor doctor = getById(id);
        return new ArrayList<>(doctor.getTickets());
    }

    public List<Ticket> getTickets(UUID uuid) {
        Doctor doctor = getByUuid(uuid);
        return new ArrayList<>(doctor.getTickets());
    }

    /**
     * Get slots for specified doctor.
     *
     * @param id id of doctor.
     * @return List of TimeSlots.
     */
    public List<TimeSlot> getSlots(Long id) {
        Doctor doctor = getById(id);

        return getSlotsFromModel(doctor);
    }

    /**
     * Get slots for specified doctor.
     *
     * @param uuid uuid of doctor.
     * @return List of TimeSlots.
     */
    public List<TimeSlot> getSlots(UUID uuid) {
        Doctor doctor = getByUuid(uuid);

        return getSlotsFromModel(doctor);
    }
}
