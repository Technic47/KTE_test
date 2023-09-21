package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.*;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.repositories.CustomerRepository;

import java.util.*;

/**
 * Service providing methods for Customer model.
 */
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
}
