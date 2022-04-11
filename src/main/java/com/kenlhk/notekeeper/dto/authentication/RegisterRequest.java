package com.kenlhk.notekeeper.dto.authentication;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterRequest {

    @NotBlank(message = "Please enter a username.")
    private String username;

    @NotBlank(message = "Please enter a password.")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    private String password;

    @NotBlank(message = "Please enter a password.")
    @Size(min = 8, message = "Password must contain at least 8 characters.")
    private String password2;

    @Email
    private String email;
}
