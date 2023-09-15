package ru.ktelabs.test.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.configs.security.jwt.JwtTokenProvider;
import ru.ktelabs.test.models.dto.AuthenticationRequestDto;
import ru.ktelabs.test.models.users.UserModel;
import ru.ktelabs.test.services.CustomUserDetailsService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationControllerREST {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userService;

    public AuthenticationControllerREST(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Operation(summary = "Get token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in. Token sent.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid username or password",
                    content = @Content)})
    @PostMapping("/login")
    public ResponseEntity getAuthUser(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserModel user = userService.loadUserByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getAuthorities());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
