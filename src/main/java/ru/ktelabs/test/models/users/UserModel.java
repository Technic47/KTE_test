package ru.ktelabs.test.models.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ktelabs.test.models.AbstractEntity;
import ru.ktelabs.test.models.dto.AbstractDto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserModel extends AbstractEntity implements UserDetails {
    @Column(unique = true)
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 255)
    private String username;

    @Column(unique = true)
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 255)
    private String email;
    @Column(name = "active")
    private boolean active;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "password", length = 1000)
    private String password;

    @Column(name = "created")
    @CreatedDate
    protected Date created;

    @Column(name = "updated")
    @LastModifiedDate
    protected Date updated;

    @Column(name = "status")
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> authorities = new HashSet<>();

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public AbstractDto createDTO() {
        return null;
    }

    public UserModel() {
        super();
    }

    @Override
    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
