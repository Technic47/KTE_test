package ru.ktelabs.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.ktelabs.test.models.dto.CustomerDTO;

import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.ktelabs.test.models.Gender.FEMALE;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("pavel")
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();
    private static final String LONG_VALUE = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789011";

    @Test
    void indexTest() throws Exception {
        mockMvc.perform(get("/api/users/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    void createTest() throws Exception {
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
                .andExpect(jsonPath("$", hasSize(9)));
    }

    @Test
    void createTestErrors() throws Exception {
        CustomerDTO newDtoWrong = new CustomerDTO(LONG_VALUE, LONG_VALUE, LONG_VALUE, null, new GregorianCalendar(2000, 11, 10));

        mockMvc.perform(post("/api/users/customers")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(4)));

        mockMvc.perform(get("/api/users/customers"))
                .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    void updateTest() throws Exception {
        CustomerDTO newDto = new CustomerDTO("test", "test", "test", FEMALE, new GregorianCalendar(2000, 11, 10));
        mockMvc.perform(put("/api/users/customers/1")
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
    }

    @Test
    void updateTestErrors() throws Exception {
        CustomerDTO newDtoWrong = new CustomerDTO(LONG_VALUE, LONG_VALUE, LONG_VALUE, null, new GregorianCalendar(2000, 11, 10));

        mockMvc.perform(put("/api/users/customers/1")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(3)));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/users/customers/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(get("/api/users/customers"))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    void deleteTestError() throws Exception {
        mockMvc.perform(delete("/api/users/customers/1111"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/users/customers"))
                .andExpect(jsonPath("$", hasSize(8)));
    }
}