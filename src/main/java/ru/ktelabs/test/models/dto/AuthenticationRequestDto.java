package ru.ktelabs.test.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;

public class AuthenticationRequestDto {
    @NotEmpty
    @Max(100)
    protected String username;
    @NotEmpty
    @Max(100)
    protected String password;

    public AuthenticationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthenticationRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
