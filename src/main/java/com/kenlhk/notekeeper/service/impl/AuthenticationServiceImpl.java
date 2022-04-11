package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.domain.User;
import com.kenlhk.notekeeper.exception.EmailException;
import com.kenlhk.notekeeper.exception.PasswordException;
import com.kenlhk.notekeeper.exception.UserException;
import com.kenlhk.notekeeper.repository.UserRepository;
import com.kenlhk.notekeeper.security.JwtProvider;
import com.kenlhk.notekeeper.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(User user, String password2) {

        if(!user.getPassword().equals(password2)){
            throw new PasswordException("Passwords do not match.");
        }

        if(userRepository.findByUsername((user.getUsername())).isPresent()){
            throw new UserException("Username is already existed.");
        }

        if(userRepository.findByEmail((user.getEmail())).isPresent()){
            throw new EmailException("Email is already in use.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtProvider.createToken(user);
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", user.getUsername());
        credentials.put("token", token);

        return credentials;
    }

    @Override
    public Map<String, String> login(User user) {

        userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new UserException("User does not exist."));

        String token = jwtProvider.createToken(user);
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", user.getUsername());
        credentials.put("token", token);

        return credentials;
    }
}
