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
import ru.ktelabs.test.models.Cabinet;
import ru.ktelabs.test.models.dto.CabinetDTO;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails("pavel")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CabinetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();

    @Test
    void indexTest() throws Exception {
        mockMvc.perform(get("/api/users/cabinets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void showTest() throws Exception {
        mockMvc.perform(get("/api/users/cabinets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.number", is(100)));

        mockMvc.perform(get("/api/users/cabinets/1111"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void showTestError() throws Exception {
        mockMvc.perform(get("/api/users/cabinets/1111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void createTest() throws Exception {
        CabinetDTO newDto = new CabinetDTO(300);
        mockMvc.perform(post("/api/users/cabinets")
                        .content(om.writeValueAsString(newDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.number", is(300)));

        mockMvc.perform(get("/api/users/cabinets"))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void createTestError() throws Exception {
        CabinetDTO newDtoWrong = new CabinetDTO();
        mockMvc.perform(post("/api/users/cabinets")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)));

        newDtoWrong = new CabinetDTO(-300);
        mockMvc.perform(post("/api/users/cabinets")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)));

        newDtoWrong = new CabinetDTO(200);
        mockMvc.perform(post("/api/users/cabinets")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)));

        mockMvc.perform(get("/api/users/cabinets"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void updateTest() throws Exception {
        Cabinet newCabinet = new Cabinet(300);
        mockMvc.perform(put("/api/users/cabinets/1")
                .content(om.writeValueAsString(newCabinet))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.number", is(300)));
    }

    @Test
    void updateTestError() throws Exception {
        CabinetDTO newDtoWrong = new CabinetDTO();
        mockMvc.perform(put("/api/users/cabinets/1")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)));

        newDtoWrong = new CabinetDTO(-300);
        mockMvc.perform(put("/api/users/cabinets/1")
                        .content(om.writeValueAsString(newDtoWrong))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors", hasSize(1)));
    }

    @Test
    void deleteTest() throws Exception {
        mockMvc.perform(delete("/api/users/cabinets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deleteTestError() throws Exception {
        mockMvc.perform(delete("/api/users/cabinets/1111"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}