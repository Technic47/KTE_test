package ru.ktelabs.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.ktelabs.test.models.Gender;
import ru.ktelabs.test.models.dto.CabinetDTO;
import ru.ktelabs.test.models.dto.CustomerDTO;

import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.ktelabs.test.models.Gender.FEMALE;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("pavel")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void indexTest() throws Exception {
        mockMvc.perform(get("/api/users/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    @Order(2)
    void createTest() throws Exception {
        ObjectMapper om = new ObjectMapper();
        CustomerDTO newDto = new CustomerDTO("test", "test", "test", FEMALE, new GregorianCalendar(2000, 11, 10));
        mockMvc.perform(post("/api/users/customers")
                        .content(om.writeValueAsString(newDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.firstName", is("test")))
                .andExpect(jsonPath("$.secondName", is("test")))
                .andExpect(jsonPath("$.givenName", is("test")))
                .andExpect(jsonPath("$.gender", is("FEMALE")))
                .andExpect(jsonPath("$.birthDate").exists());

        mockMvc.perform(get("/api/users/customers"))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getTimeSlots() throws Exception {
        mockMvc.perform(get("/api/users/customers/1/slots"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetTimeSlots() throws Exception {
        mockMvc.perform(get("/api/users/customers/slots")
                        .param("customerId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(get("/api/users/customers/slots"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}