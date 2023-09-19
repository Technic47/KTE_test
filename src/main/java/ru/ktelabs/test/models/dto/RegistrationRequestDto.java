package ru.ktelabs.test.models.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import ru.ktelabs.test.models.users.UserModel;

public class RegistrationRequestDto extends AuthenticationRequestDto {
    @NotEmpty
    @Max(100)
    private String email;

    public UserModel toUserModel(){
        UserModel newModel = new UserModel(this.username, this.password);
        newModel.setEmail(this.email);
        return newModel;
    }

    public RegistrationRequestDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
