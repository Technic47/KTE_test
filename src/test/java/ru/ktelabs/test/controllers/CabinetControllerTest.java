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

    @Test
    @Order(1)
    void indexTest() throws Exception {
        mockMvc.perform(get("/api/users/cabinets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @Order(2)
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
    @Order(3)
    void createTest() throws Exception {
        ObjectMapper om = new ObjectMapper();
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
    @Order(4)
    void updateTest() throws Exception {
        ObjectMapper om = new ObjectMapper();
        Cabinet newCabinet = new Cabinet(300);
        mockMvc.perform(put("/api/users/cabinets/1")
                .content(om.writeValueAsString(newCabinet))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.number", is(300)));

        mockMvc.perform(get("/api/users/cabinets/1"))
                .andExpect(jsonPath("$.number", is(300)));
    }

//    @Test
//    void deleteTest() throws Exception {
//        mockMvc.perform(delete("/api/users/cabinets/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("true"));
//    }
}