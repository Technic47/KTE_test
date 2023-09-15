package ru.ktelabs.test.models.dto;


import ru.ktelabs.test.models.users.UserModel;

public class RegistrationRequestDto extends AuthenticationRequestDto {
    private String email;

    public UserModel toUserModel(){
        UserModel newModel = new UserModel(this.username, this.password);
        newModel.setEmail(this.email);
        return newModel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
