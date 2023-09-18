package ru.ktelabs.test.configs.security;

import jakarta.persistence.*;
import ru.ktelabs.test.models.AbstractEntity;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.users.UserModel;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
public class VerificationToken extends AbstractEntity {
    private static final int EXPIRATION = 60 * 24;

    private String token;

    @OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserModel user;

    private Date expiryDate;

    @Override
    public AbstractDto createDTO() {
        return null;
    }

    public VerificationToken() {
    }

    public VerificationToken(String token, UserModel user) {
        this.token = token;
        this.user = user;
        this.expiryDate = this.calculateExpiryDate();
    }

    public void updateToken(){
        this.expiryDate = this.calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
