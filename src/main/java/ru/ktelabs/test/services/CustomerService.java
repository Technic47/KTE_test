package ru.ktelabs.test.services;

import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.repositories.CustomerRepository;

import java.util.Calendar;

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

        old.setUpdated(Calendar.getInstance());
        return save(old);
    }
}
