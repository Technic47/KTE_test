package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.Gender;
import ru.ktelabs.test.models.HumanModel;
import ru.ktelabs.test.models.builders.CustomerDTOBuilder;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends HumanModelDTO {
    public CustomerDTO(String firstName, String secondName, String givenName, Gender gender, Calendar birthDate) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.givenName = givenName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public CustomerDTO() {
    }

    public static CustomerDTO createCustomerDTO(Customer customer) {
        return new CustomerDTOBuilder()
                .setFirstName(customer.getFirstName())
                .setSecondName(customer.getSecondName())
                .setGivenName(customer.getGivenName())
                .setGender(customer.getGender())
                .setBirthDate(customer.getBirthDate())
                .createCustomerDTO();
    }

    @Override
    public Customer createHuman() {
        return new Customer(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getGivenName() {
        return givenName;
    }

    public Gender getGender() {
        return gender;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }


}
