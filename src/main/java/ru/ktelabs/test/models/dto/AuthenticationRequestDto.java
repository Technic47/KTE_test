package ru.ktelabs.test.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationRequestDto {
    @NotEmpty
    @Size(max = 100)
    protected String username;
    @NotEmpty
    @Size(max = 100)
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
