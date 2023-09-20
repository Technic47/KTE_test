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
import ru.ktelabs.test.models.dto.DoctorDTO;


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
class DoctorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/api/users/doctors"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void create() throws Exception{
        ObjectMapper om = new ObjectMapper();
        DoctorDTO newDto = new DoctorDTO("test", "test", "test", FEMALE, new GregorianCalendar(2000, 11, 10), "niero");
        mockMvc.perform(post("/api/users/doctors")
                        .content(om.writeValueAsString(newDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.firstName", is("test")))
                .andExpect(jsonPath("$.secondName", is("test")))
                .andExpect(jsonPath("$.givenName", is("test")))
                .andExpect(jsonPath("$.gender", is("FEMALE")))
                .andExpect(jsonPath("$.birthDate").exists())
                .andExpect(jsonPath("$.speciality", is("niero")));

        mockMvc.perform(get("/api/users/doctors"))
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void getTickets() throws Exception {
        mockMvc.perform(get("/api/users/doctors/1/tickets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void getTicketsById() throws Exception {
        mockMvc.perform(get("/api/users/doctors/tickets")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(6)));

        mockMvc.perform(get("/api/users/doctors/tickets"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getTimeSlotsTest() throws Exception {
        mockMvc.perform(get("/api/users/doctors/1/slots"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void getTimeSlotsTestError() throws Exception {
        mockMvc.perform(get("/api/users/doctors/111/slots"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void getTimeSlots2Test() throws Exception {
        mockMvc.perform(get("/api/users/doctors/slots")
                        .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void getTimeSlots2TestError() throws Exception {
        mockMvc.perform(get("/api/users/doctors/slots")
                        .param("id", "11111"))
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/users/doctors/slots"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}