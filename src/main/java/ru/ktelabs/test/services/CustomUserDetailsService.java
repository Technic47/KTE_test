package ru.ktelabs.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ktelabs.test.models.users.UserModel;

/**
 * Service for authorisation of users.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    private String adminName;
    private String adminPass;

    @Autowired
    public CustomUserDetailsService(UserService userService,
                                    @Value("${admin.name}") String adminName,
                                    @Value("${admin.pass}") String adminPass) {
        this.userService = userService;
        this.adminName = adminName;
        this.adminPass = adminPass;
        this.adminSave();
    }

    /**
     * Find user by email and return if it is present.
     *
     * @param userName email to search
     * @return UserDetails object
     * @throws UsernameNotFoundException
     */
    @Override
    public UserModel loadUserByUsername(String userName) throws RuntimeException {
        UserModel user = userService.findByUsernameOrNull(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + userName);
        }
        return user;
    }


    /**
     * Creates built-in admin user and send it to DB.
     * Deletes admin credentials.
     */
    private void adminSave() {
        UserModel adminUser = new UserModel();
        adminUser.setUsername(adminName);
        adminUser.setPassword(adminPass);
        adminUser.setEmail("AdminIsDumb!");
        this.userService.createAdmin(adminUser);
        this.adminName = "***";
        this.adminPass = "***";
    }
}
