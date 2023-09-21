package ru.ktelabs.test.models.dto;


import jakarta.validation.constraints.*;
import ru.ktelabs.test.models.users.UserModel;

public class RegistrationRequestDto extends AuthenticationRequestDto {
    //RFC 5322
    @NotEmpty
    @Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Size(max = 100)
    private String email;

    public UserModel toUserModel(){
        UserModel newModel = new UserModel(this.username, this.password);
        newModel.setEmail(this.email);
        return newModel;
    }

    public RegistrationRequestDto() {
    }

    public RegistrationRequestDto(String username, String password, String email) {
        super(username, password);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
