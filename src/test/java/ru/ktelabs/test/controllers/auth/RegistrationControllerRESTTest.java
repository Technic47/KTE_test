package ru.ktelabs.test.controllers.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.ktelabs.test.models.dto.AuthenticationRequestDto;
import ru.ktelabs.test.models.dto.RegistrationRequestDto;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerRESTTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper om = new ObjectMapper();
    public static String TEST_NAME = "Ivan";
    public static String TEST_EMAIL = "ivan@mail.com";
    public static String TEST_PASS = "12345";

    @Test
    void newUserTest() throws Exception {
        RegistrationRequestDto regDto = new RegistrationRequestDto(TEST_NAME, TEST_PASS, TEST_EMAIL);
        mockMvc.perform(post("/api/registration")
                        .content(om.writeValueAsString(regDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.created").isNotEmpty())
                .andExpect(jsonPath("$.username", is(TEST_NAME)))
                .andExpect(jsonPath("$.email", is(TEST_EMAIL)))
                .andExpect(jsonPath("$.active", is(true)))
                .andExpect(jsonPath("$.enabled", is(true)))
                .andExpect(jsonPath("$.authorities", hasSize(1)))
                .andExpect(jsonPath("$.authorities", contains("ROLE_USER")));

        AuthenticationRequestDto dto = new AuthenticationRequestDto("Ivan", "12345");
        mockMvc.perform(post("/api/auth/login")
                        .content(om.writeValueAsString(dto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("Ivan")));
    }

    @Test
    void newUserTestError() throws Exception {
        RegistrationRequestDto regDto = new RegistrationRequestDto(TEST_NAME, null, TEST_EMAIL);
        mockMvc.perform(post("/api/registration")
                        .content(om.writeValueAsString(regDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        regDto = new RegistrationRequestDto(null, TEST_PASS, TEST_EMAIL);
        mockMvc.perform(post("/api/registration")
                        .content(om.writeValueAsString(regDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        regDto = new RegistrationRequestDto(TEST_NAME, TEST_PASS, null);
        mockMvc.perform(post("/api/registration")
                        .content(om.writeValueAsString(regDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}