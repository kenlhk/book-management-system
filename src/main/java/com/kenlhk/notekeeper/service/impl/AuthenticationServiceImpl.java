package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.domain.User;
import com.kenlhk.notekeeper.exception.ApiRequestException;
import com.kenlhk.notekeeper.repository.UserRepository;
import com.kenlhk.notekeeper.security.JwtProvider;
import com.kenlhk.notekeeper.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final AuthenticationManager authenticationManager;

    @Override
    public Map<String, String> register(User user, String password2) {

        if(!user.getPassword().equals(password2)){
            throw new ApiRequestException("Passwords do not match.", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.findByUsername((user.getUsername())).isPresent()){
            throw new ApiRequestException("Username is already existed.", HttpStatus.BAD_REQUEST);
        }

        if(userRepository.findByEmail((user.getEmail())).isPresent()){
            throw new ApiRequestException("Email is already in use.", HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = jwtProvider.generateToken(user);
        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", user.getUsername());
        credentials.put("token", token);

        return credentials;
    }

    @Override
    public Map<String, String> login(User user) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
            String token = jwtProvider.generateToken(user);
            Map<String, String> credentials = new HashMap<>();
            credentials.put("username", user.getUsername());
            credentials.put("token", token);
            return credentials;
        } catch (AuthenticationException e){
            throw new ApiRequestException("Username or password invalid.", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User findCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
        return user;
    }

    @Override
    public Boolean isCurrentUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ApiRequestException("User not found.", HttpStatus.NOT_FOUND));
        return currentUser.getUsername().equals(user.getUsername());
    }
}
