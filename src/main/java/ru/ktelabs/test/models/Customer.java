package ru.ktelabs.test.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.CustomerDTO;

import java.util.*;

@Entity
public class Customer extends HumanModel{
    public Customer(String firstName, String secondName, String givenName, Gender gender, Calendar birthDate) {
        super(firstName, secondName, givenName, gender, birthDate);
    }

    public Customer(Long id, UUID uuid, String firstName, String secondName, String givenName, Gender gender, Calendar birthDate, int age, Calendar created, Calendar updated, Set<Ticket> tickets) {
        super(id, uuid, firstName, secondName, givenName, gender, birthDate, age, created, updated, tickets);
    }

    public Customer(CustomerDTO dto){
        super();
        this.firstName = dto.getFirstName();
        this.secondName = dto.getSecondName();
        this.givenName = dto.getGivenName();
        this.gender = dto.getGender();
        this.birthDate = dto.getBirthDate();
        this.age = new GregorianCalendar().get(Calendar.YEAR) - birthDate.get((Calendar.YEAR));
    }

    @Override
    public AbstractDto createDTO() {
        return CustomerDTO.createCustomerDTO(this);
    }

    public Customer() {
        super();
    }
}
