package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.TimeSlotRepository;

@Service
public class TimeSlotService extends AbstractService<TimeSlot, TimeSlotRepository> {
    public TimeSlotService(TimeSlotRepository repository) {
        super(repository);
    }

    @Override
    public TimeSlot update(TimeSlot old, TimeSlot newEntity) {
        old.setStartTime(newEntity.getStartTime());
        old.setFinishTime(newEntity.getFinishTime());
        return save(old);
    }
}
