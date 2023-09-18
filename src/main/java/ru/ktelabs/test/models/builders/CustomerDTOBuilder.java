package ru.ktelabs.test.models.builders;

import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Gender;
import ru.ktelabs.test.models.dto.CustomerDTO;

import java.util.Calendar;

public class CustomerDTOBuilder {
    private String firstName;
    private String secondName;
    private String givenName;
    private Gender gender;
    private Calendar birthDate;
    private Customer customer;

    public CustomerDTOBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDTOBuilder setSecondName(String secondName) {
        this.secondName = secondName;
        return this;
    }

    public CustomerDTOBuilder setGivenName(String givenName) {
        this.givenName = givenName;
        return this;
    }

    public CustomerDTOBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public CustomerDTOBuilder setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public CustomerDTOBuilder setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public CustomerDTO createCustomerDTO() {
        return new CustomerDTO(firstName, secondName, givenName, gender, birthDate);
    }
}