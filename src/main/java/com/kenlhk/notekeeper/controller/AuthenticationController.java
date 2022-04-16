package com.kenlhk.notekeeper.controller;

import com.kenlhk.notekeeper.dto.authentication.AuthenticationResponse;
import com.kenlhk.notekeeper.dto.authentication.LoginRequest;
import com.kenlhk.notekeeper.dto.authentication.RegisterRequest;
import com.kenlhk.notekeeper.mapper.AuthenticationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationMapper authenticationMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationMapper.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationMapper.login(request));
    }
}
