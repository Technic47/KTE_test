package ru.ktelabs.test.repositories;

import org.springframework.stereotype.Repository;
import ru.ktelabs.test.models.Customer;

@Repository
public interface CustomerRepository extends CommonRepository<Customer> {
}
