package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.*;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.repositories.CustomerRepository;

import java.util.*;

@Service
public class CustomerService extends HumanModelService<Customer, CustomerRepository> {
    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    @Override
    public Customer update(Customer old, Customer newEntity) {
        old.setFirstName(newEntity.getFirstName());
        old.setSecondName(newEntity.getSecondName());
        old.setGivenName(newEntity.getGivenName());
        old.setBirthDate(newEntity.getBirthDate());
        old.setGender(newEntity.getGender());

        old.setUpdated(Calendar.getInstance());
        return save(old);
    }

    /**
     * Get all slots occupied by specified customer and date.
     *
     * @param id id of customer.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getSlots(Long id, Calendar date) {
        Customer customer = getById(id);
        return getSlotsFromModel(customer, date);
    }

    /**
     * Get all slots occupied by specified customer and date.
     *
     * @param uuid uuid of customer.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getSlots(UUID uuid, Calendar date) {
        Customer customer = getByUuid(uuid);

        return getSlotsFromModel(customer, date);
    }

    /**
     * Get all slots for specified customer.
     *
     * @param id id of doctor.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getAllSlots(Long id) {
        Customer doctor = getById(id);

        return getAllSlotsFromModel(doctor);
    }

    /**
     * Get all slots for specified customer.
     *
     * @param uuid uuid of doctor.
     * @return List of TimeSlots.
     */
    public List<TimeSlotDTO> getAllSlots(UUID uuid) {
        Customer doctor = getByUuid(uuid);

        return getAllSlotsFromModel(doctor);
    }
}
