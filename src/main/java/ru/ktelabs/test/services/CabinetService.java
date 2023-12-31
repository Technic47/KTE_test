package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.customExceptions.ResourceNotFoundException;
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.CabinetRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service providing methods for Cabinet model.
 */
@Service
public class CabinetService extends AbstractService<Cabinet, CabinetRepository> {
    public CabinetService(CabinetRepository repository) {
        super(repository);
    }

    @Override
    public Cabinet update(Cabinet old, Cabinet newEntity) {
        old.setNumber(newEntity.getNumber());
        old.setTimeslots(newEntity.getTimeslots());
        return save(old);
    }

    /**
     * Find cabinet by unique number.
     *
     * @param number number to find.
     * @return found entity.
     */
    public Cabinet findByNumber(int number) {
        Optional<Cabinet> result = repository.findByNumber(number);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No cabinet with number: " + number);
        } else return result.get();
    }

    /**
     * Check existence of Cabinet by int number.
     *
     * @param number number to check.
     * @return boolean result.
     */
    public boolean existByNumber(int number) {
        return repository.existsByNumber(number);
    }

    public Cabinet setSlots(Cabinet cabinet, Collection<TimeSlot> slots) {
        cabinet.setTimeslots(new HashSet<>(slots));
        return save(cabinet);
    }

    public Cabinet addSlot(int number, TimeSlot slot) {
        Cabinet cabinet = findByNumber(number);
        cabinet.getTimeslots().add(slot);
        return save(cabinet);
    }

    public void removeSlot(Cabinet cabinet, TimeSlot slot) {
        Set<TimeSlot> timeslots = cabinet.getTimeslots();
        timeslots.remove(slot);
        save(cabinet);
    }
}
