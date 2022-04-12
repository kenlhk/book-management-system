package com.kenlhk.notekeeper.security;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String message) {
        super(message);
    }
}
