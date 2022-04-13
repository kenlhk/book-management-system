package com.kenlhk.notekeeper.dto.authentication;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String username;
    private String token;
}
