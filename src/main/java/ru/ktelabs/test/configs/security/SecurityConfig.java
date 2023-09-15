package ru.ktelabs.test.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ktelabs.test.configs.security.jwt.CustomAuthorizationFilter;
import ru.ktelabs.test.configs.security.jwt.JwtTokenProvider;
import ru.ktelabs.test.exceptionHandlers.CustomAuthenticationFailureHandler;
import ru.ktelabs.test.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests((auth) -> {
                    auth.requestMatchers("/api/auth/login/*").permitAll();
                    auth.requestMatchers("/api/registration/*").permitAll();
                    auth.requestMatchers("/api/users/**").authenticated();
                })
                .addFilterBefore(new CustomAuthorizationFilter(jwtTokenProvider, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authProvider);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
}
