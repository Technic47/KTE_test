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
import ru.ktelabs.test.models.dto.TimeSlotDTO;

import java.util.Calendar;
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
class TimeSlotControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/api/users/timeSlots"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(18)));
    }

    @Test
    void create() throws Exception {
        Calendar start = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar finish = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        ObjectMapper om = new ObjectMapper();
        TimeSlotDTO newDto = new TimeSlotDTO(1L, start, finish, 200);
        mockMvc.perform(post("/api/users/timeSlots")
                        .content(om.writeValueAsString(newDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.startTime", is("2022-12-12 15:00:00")))
                .andExpect(jsonPath("$.finishTime", is("2022-12-12 15:30:00")))
                .andExpect(jsonPath("$.cabinetNumber", is(200)));

        mockMvc.perform(get("/api/users/timeSlots"))
                .andExpect(jsonPath("$", hasSize(19)));
    }

    @Test
    void show() throws Exception {
        mockMvc.perform(get("/api/users/timeSlots/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.startTime").isNotEmpty())
                .andExpect(jsonPath("$.finishTime").isNotEmpty())
                .andExpect(jsonPath("$.cabinetNumber", is(100)));
    }

    @Test
    void update() throws Exception {
        Calendar start = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 0);
        Calendar finish = new GregorianCalendar(2022, Calendar.DECEMBER, 12, 18, 30);
        ObjectMapper om = new ObjectMapper();
        TimeSlotDTO newDto = new TimeSlotDTO(1L, start, finish, 200);
        mockMvc.perform(put("/api/users/timeSlots/1")
                        .content(om.writeValueAsString(newDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.startTime", is("2022-12-12 15:00:00")))
                .andExpect(jsonPath("$.finishTime", is("2022-12-12 15:30:00")))
                .andExpect(jsonPath("$.cabinetNumber", is(200)));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/users/timeSlots/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void populate() throws Exception {
        mockMvc.perform(post("/api/users/timeSlots/generate")
                        .param("cabinetNumber", "200")
                        .param("year", "2000")
                        .param("month", "6")
                        .param("day", "2")
                        .param("periodMinutes", "30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(18)));

        mockMvc.perform(post("/api/users/timeSlots/generate")
                        .param("cabinetNumber", "200")
                        .param("year", "2000")
                        .param("month", "6")
                        .param("day", "2"))
                .andExpect(jsonPath("$", hasSize(36)));
    }

    @Test
    void populateMonth() throws Exception {
        mockMvc.perform(post("/api/users/timeSlots/generate")
                        .param("cabinetNumber", "200")
                        .param("year", "2000")
                        .param("month", "6")
                        .param("periodMinutes", "30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(540)));
    }

    @Test
    void freeSlots() throws Exception {
        mockMvc.perform(get("/api/users/timeSlots/free")
                        .param("cabinetNumber", "100")
                        .param("year", "2023")
                        .param("month", "10")
                        .param("day", "16"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(4)));

        mockMvc.perform(get("/api/users/timeSlots/free")
                        .param("cabinetNumber", "200")
                        .param("year", "2023")
                        .param("month", "10")
                        .param("day", "16"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void freeAllSlots() throws Exception {
        mockMvc.perform(get("/api/users/timeSlots/free")
                        .param("year", "2023")
                        .param("month", "10")
                        .param("day", "16"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(6)));

        mockMvc.perform(get("/api/users/timeSlots/free"))
                .andExpect(status().is4xxClientError());
    }
}