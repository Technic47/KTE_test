package ru.ktelabs.test.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ru.ktelabs.test.customExceptions.RegistrationException;
import ru.ktelabs.test.models.dto.RegistrationRequestDto;
import ru.ktelabs.test.models.users.UserModel;
import ru.ktelabs.test.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.ktelabs.test.controllers.auth.RegistrationControllerRESTTest.*;
import static ru.ktelabs.test.models.users.UserRole.ROLE_ADMIN;
import static ru.ktelabs.test.models.users.UserRole.ROLE_USER;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/SQL_scripts/create-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/SQL_scripts/clean-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {
    private UserService userService;
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public static final UserModel TEST_USER = new UserModel(TEST_NAME, TEST_EMAIL, TEST_PASS);

    @BeforeEach
    void setUp() {
        this.userService = new UserService(repository, passwordEncoder);
    }

    @Test
    @Order(1)
    void createUser() {
        assertTrue(userService.createUser(TEST_USER));

        UserModel checkUser = userService.findByUsernameOrNull(TEST_NAME);

        assertThat(checkUser).isNotNull();
        assertTrue(checkUser.isActive());
        assertNotEquals(TEST_PASS, checkUser.getPassword());
        assertTrue(checkUser.getAuthorities().contains(ROLE_USER));
        assertFalse(checkUser.getAuthorities().contains(ROLE_ADMIN));
    }

    @Test
    @Order(2)
    void registerNewUserAccountTest(){
        RegistrationRequestDto regDto = new RegistrationRequestDto(TEST_NAME, TEST_PASS, TEST_EMAIL);

        UserModel userModel = userService.registerNewUserAccount(regDto);
        assertEquals(TEST_NAME, userModel.getUsername());
        assertNotNull(userModel.getPassword());
        assertEquals(TEST_EMAIL, userModel.getEmail());
    }

    @Test
    @Order(3)
    void registerNewUserAccountTestError(){
        RegistrationRequestDto regDto = new RegistrationRequestDto(TEST_NAME, TEST_PASS, "email2@mail.il");

        assertThrows(RegistrationException.class, () -> userService.registerNewUserAccount(regDto));
    }

    @Test
    @Order(4)
    void createAdmin() {
        userService.createAdmin(TEST_USER);

        UserModel checkUser = userService.findByUsernameOrNull(TEST_NAME);

        assertThat(checkUser).isNotNull();
        assertTrue(checkUser.isActive());
        assertNotEquals(TEST_PASS, checkUser.getPassword());
        assertTrue(checkUser.getAuthorities().contains(ROLE_ADMIN));
        assertFalse(checkUser.getAuthorities().contains(ROLE_USER));
    }

    @Test
    @Order(5)
    void createUserFalse() {
        assertTrue(userService.createUser(TEST_USER));
        assertFalse(userService.createUser(TEST_USER));
    }


    @Test
    @Order(6)
    void findByName() {
        userService.createUser(TEST_USER);
        UserModel result = userService.findByUsernameOrNull(TEST_NAME);

        assertEquals(TEST_USER.getUsername(), result.getUsername());
        assertNotNull(result.getId());
        assertNotNull(result.getPassword());
    }

    @Test
    @Order(7)
    void findByNameNull() {
        assertNull(userService.findByUsernameOrNull(TEST_NAME));
    }

    @Test
    @Order(8)
    void userToAdmin(){
        userService.createUser(TEST_USER);
        UserModel user = userService.findByUsernameOrNull(TEST_NAME);
        userService.userToAdmin(user.getId());

        user = userService.findByUsernameOrNull(TEST_NAME);
        assertThat(user.getAuthorities()).hasSize(2).contains(ROLE_ADMIN);
    }

    @Test
    @Order(9)
    void adminToUser(){
        userService.createAdmin(TEST_USER);
        UserModel user = userService.findByUsernameOrNull(TEST_NAME);
        userService.adminToUser(user.getId());

        user = userService.findByUsernameOrNull(TEST_NAME);
        assertThat(user.getAuthorities()).hasSize(0).doesNotContain(ROLE_ADMIN);
    }

    @Test
    @Order(10)
    void update(){
        userService.createUser(TEST_USER);
        UserModel newModel = new UserModel("NewName", "NewEmail@mail.com", "NewPass");
        UserModel user = userService.findByUsernameOrNull(TEST_NAME);
        userService.update(user, newModel);

        user = userService.findByUsernameOrNull("NewName");

        assertEquals("NewName", user.getUsername());
        assertNotEquals(TEST_PASS, user.getPassword());
    }
}