package ru.ktelabs.test.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Gender;
import ru.ktelabs.test.models.builders.CustomerDTOBuilder;
import ru.ktelabs.test.models.builders.DoctorDTOBuilder;

import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends AbstractDto {
    @NotNull
    private String firstName;
    @NotNull
    private String secondName;
    private String givenName;
    @NotNull
    @JsonProperty(value = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @JsonFormat(pattern = "yyyy-MM-DD")
    private Calendar birthDate;

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
