package ru.ktelabs.test.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Ticket;
import ru.ktelabs.test.models.TimeSlot;
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

        old.setUpdated(Calendar.getInstance());
        return save(old);
    }

    /**
     * Get slots occupied by specified customer.
     *
     * @param id id of customer.
     * @return List of TimeSlots.
     */
    public List<TimeSlot> getSlots(Long id) {
        Customer customer = getById(id);

        return getCustomerSlots(customer);
    }

    public List<TimeSlot> getSlots(UUID uuid) {
        Customer customer = getByUuid(uuid);

        return getCustomerSlots(customer);
    }

    private List<TimeSlot> getCustomerSlots(Customer customer){
        List<TimeSlot> timeSlotList = new ArrayList<>();
        Set<Ticket> tickets = customer.getTickets();
        tickets.forEach(ticket -> timeSlotList.add(ticket.getTimeSlot()));

        return timeSlotList;
    }
}
