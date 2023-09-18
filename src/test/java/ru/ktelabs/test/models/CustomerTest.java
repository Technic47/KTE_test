package ru.ktelabs.test.models;

import org.junit.jupiter.api.Test;
import ru.ktelabs.test.models.dto.CustomerDTO;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.ktelabs.test.models.Gender.MALE;

class CustomerTest {
    @Test
    void creationTest() {
        CustomerDTO dto = new CustomerDTO("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Customer customer = new Customer(dto);
        assertThat(customer.getUuid()).isNotNull();
        assertThat(customer.getFirstName())
                .isNotNull()
                .isEqualTo("pavel");
        assertThat(customer.getSecondName())
                .isNotNull()
                .isEqualTo("kuznetsov");
        assertThat(customer.getGivenName())
                .isNotNull()
                .isEqualTo("andreevich");
        assertThat(customer.getGender())
                .isNotNull()
                .isEqualTo(MALE);
        assertThat(customer.getBirthDate())
                .isNotNull();
        assertThat(customer.getAge())
                .isNotNull();
    }

    @Test
    void equalsTest(){
        Customer customer1 = new Customer("pavel", "kuznetsov", "andreevich", MALE, new GregorianCalendar());
        Customer customer2 = new Customer("ivan", "kuznetsov", "andreevich", MALE, new GregorianCalendar());

        assertNotEquals(customer1, customer2);
    }
}