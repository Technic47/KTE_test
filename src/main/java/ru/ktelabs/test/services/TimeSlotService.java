package ru.ktelabs.test.services;

import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.repositories.TimeSlotRepository;

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
