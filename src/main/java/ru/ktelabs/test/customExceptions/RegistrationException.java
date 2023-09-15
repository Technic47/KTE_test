package ru.ktelabs.test.customExceptions;

import org.springframework.security.core.AuthenticationException;

public class RegistrationException extends AuthenticationException {
    public RegistrationException(String msg) {
        super(msg);
    }
}
