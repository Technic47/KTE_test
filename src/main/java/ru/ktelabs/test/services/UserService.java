package ru.ktelabs.test.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ktelabs.test.customExceptions.RegistrationException;
import ru.ktelabs.test.models.users.UserBuilder;
import ru.ktelabs.test.models.users.UserModel;
import ru.ktelabs.test.models.users.UserRole;
import ru.ktelabs.test.models.dto.RegistrationRequestDto;
import ru.ktelabs.test.repositories.UserRepository;

import java.util.Date;
import java.util.List;

import static ru.ktelabs.test.models.users.UserRole.ROLE_ADMIN;
import static ru.ktelabs.test.models.users.UserRole.ROLE_USER;

/**
 * Service providing methods for UserModel.
 * Provides main User registration logic.
 */
@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create user with ROLE_USER
     *
     * @param userModel prepared user record
     * @return false if user already exists.
     */
    public boolean createUser(UserModel userModel) {
        return this.constructRecordAndSave(userModel, ROLE_USER) != null;
    }

    /**
     * Create user with ROLE_ADMIN
     *
     * @param userModel prepared user record
     */
    public void createAdmin(UserModel userModel) {
        this.constructRecordAndSave(userModel, ROLE_ADMIN);
    }

    private UserModel constructRecordAndSave(UserModel userModel, UserRole role) {
        if (this.findByUsernameOrNull(userModel.getUsername()) != null) {
            return null;
        }
        UserModel newUser = new UserBuilder(userModel.getUsername(), userModel.getEmail(), userModel.getPassword())
                .encodePassword(this.passwordEncoder).setActive(true)
                .addRole(role).build();
        return repository.save(newUser);
    }

    /**
     * Finds user record with specified username.
     *
     * @param username name for search.
     * @return UserModel wth record from DB.
     */
    public UserModel findByUsernameOrNull(String username) {
        return repository.findByUsername(username);
    }

//    public UserModel findByEmailOrNull(String email) {
//        return this.repository.findByEmail(email);
//    }

    private boolean emailExist(String email) {
        return repository.findByEmail(email) != null;
    }

//    /**
//     * Find user records containing argument username in username field.
//     *
//     * @param username search value
//     * @return list of matching records.
//     */
//    public List<UserModel> findByUsernameContainingIgnoreCase(String username) {
//        return repository.findByUsernameContainingIgnoreCase(username);
//    }

    /**
     * Adds admin role to user.
     *
     * @param id id of user.
     */
    public void userToAdmin(Long id) {
        UserModel model = repository.findById(id).get();
        model.getAuthorities().add(ROLE_ADMIN);
        repository.save(model);
    }

    /**
     * Removes admin role from user.
     *
     * @param id id of user.
     */
    public void adminToUser(Long id) {
        UserModel model = repository.findById(id).get();
        model.getAuthorities().remove(ROLE_ADMIN);
        repository.save(model);
    }

    /**
     * Update userName or password in user record.
     *
     * @param oldItem    userRecord for changing
     * @param updateItem object with new credentials
     */
    public UserModel update(UserModel oldItem, UserModel updateItem) {
        String newName = updateItem.getUsername();
        if (!newName.isEmpty()) {
            oldItem.setUsername(newName);
        }
        String newEmail = updateItem.getEmail();
        if (!newEmail.isEmpty()) {
            oldItem.setEmail(newEmail);
        }
        if (!updateItem.getPassword().isEmpty()) {
            String newPass = updateItem.getPassword();
            oldItem.setPassword(this.passwordEncoder.encode(newPass));
        }
        oldItem.setUpdated(new Date());
        return repository.save(oldItem);
    }

    /**
     * Create user with ROLE_USER from dto object
     *
     * @param dto prepared user record.
     * @return saved new UserModel record.
     * @throws RuntimeException if user exist.
     */
    public UserModel registerNewUserAccount(RegistrationRequestDto dto) throws RuntimeException {
        if (emailExist(dto.getEmail())) {
            throw new RegistrationException("Email exists");
        }
        return this.constructRecordAndSave(dto.toUserModel(), ROLE_USER);
    }
}
