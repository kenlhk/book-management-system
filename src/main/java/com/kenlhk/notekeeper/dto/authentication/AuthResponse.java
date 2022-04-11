package com.kenlhk.notekeeper.dto.authentication;

import lombok.Data;

@Data
public class AuthResponse {
    private String username;
    private String token;
}
