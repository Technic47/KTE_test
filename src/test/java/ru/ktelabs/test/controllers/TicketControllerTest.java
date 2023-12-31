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
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.models.dto.TicketDTO;

import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.*;
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
class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/api/users/tickets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(12)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/users/tickets")
                        .param("doctorId", "1")
                        .param("customerId", "1")
                        .param("timeSlotId", "4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(13)))
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.doctor.firstName", is("Ivan")))
                .andExpect(jsonPath("$.customer.firstName", is("Ivan")))
                .andExpect(jsonPath("$.timeSlot.cabinetNumber", is(100)));

        mockMvc.perform(get("/api/users/tickets"))
                .andExpect(jsonPath("$", hasSize(13)));

        //error - slot occupied
        mockMvc.perform(post("/api/users/tickets")
                        .param("doctorId", "2")
                        .param("customerId", "2")
                        .param("timeSlotId", "3"))
                .andExpect(status().is4xxClientError());

        //error - no params.
        mockMvc.perform(post("/api/users/tickets"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void show() throws Exception {
        mockMvc.perform(get("/api/users/tickets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.doctor").isNotEmpty())
                .andExpect(jsonPath("$.customer").isNotEmpty())
                .andExpect(jsonPath("$.timeSlot").isNotEmpty());

        mockMvc.perform(get("/api/users/tickets/1111"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(put("/api/users/tickets/1")
                        .param("doctorId", "2")
                        .param("customerId", "2")
                        .param("timeSlotId", "8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id", not(1)))
                .andExpect(jsonPath("$.uuid").isNotEmpty())
                .andExpect(jsonPath("$.doctor.firstName", is("Alex")))
                .andExpect(jsonPath("$.customer.firstName", is("Alex")))
                .andExpect(jsonPath("$.timeSlot.cabinetNumber").isNotEmpty());

        //error - slot occupied
        mockMvc.perform(put("/api/users/tickets/1")
                .param("doctorId", "2")
                .param("customerId", "2")
                .param("timeSlotId", "3"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(put("/api/users/tickets/1"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(put("/api/users/tickets/111"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/users/tickets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        mockMvc.perform(delete("/api/users/tickets/111"))
                .andExpect(status().isNotFound());
    }

    @Test
    void archiveIndexTest() throws Exception{
        mockMvc.perform(delete("/api/users/tickets/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/tickets/archive"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void archiveGetTest() throws Exception{
        mockMvc.perform(delete("/api/users/tickets/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/tickets/archive/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void archiveGetTestError() throws Exception{
        mockMvc.perform(delete("/api/users/tickets/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/tickets/archive/1111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void archiveGetByOldIdTest() throws Exception{
        mockMvc.perform(delete("/api/users/tickets/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/tickets/archive/oldId/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.oldId", is(1)));
    }

    @Test
    void archiveGetByOldIdTestError() throws Exception{
        mockMvc.perform(delete("/api/users/tickets/1"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/users/tickets/archive/oldId/1111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}