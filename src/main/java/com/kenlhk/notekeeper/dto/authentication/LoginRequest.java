package com.kenlhk.notekeeper.dto.authentication;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Please enter a username.")
    private String username;

    @NotBlank(message = "Please enter a password.")
    private String password;
}
