package ru.ktelabs.test.controllers.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.ktelabs.test.models.dto.AuthenticationRequestDto;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthenticationControllerRESTTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();


    @Test
    void logInTest() throws Exception {
        AuthenticationRequestDto dto = new AuthenticationRequestDto("pavel", "1999");
        mockMvc.perform(post("/api/auth/login")
                .content(om.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.username", is("pavel")))
                .andExpect(jsonPath("$.token", any(String.class)));
    }

    @Test
    void logInTestError() throws Exception {
        AuthenticationRequestDto dto = new AuthenticationRequestDto("pavel1", "1999");
        mockMvc.perform(post("/api/auth/login")
                        .content(om.writeValueAsString(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());

        dto = new AuthenticationRequestDto("pavel", "1999i");
        mockMvc.perform(post("/api/auth/login")
                        .content(om.writeValueAsString(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());

        mockMvc.perform(post("/api/auth/login"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(post("/api/auth/login")
                        .content(om.writeValueAsString(new AuthenticationRequestDto()))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}