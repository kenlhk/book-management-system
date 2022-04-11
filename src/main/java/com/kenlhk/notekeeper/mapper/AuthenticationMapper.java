package com.kenlhk.notekeeper.mapper;

import com.kenlhk.notekeeper.domain.User;
import com.kenlhk.notekeeper.dto.authentication.LoginRequest;
import com.kenlhk.notekeeper.dto.authentication.RegisterRequest;
import com.kenlhk.notekeeper.dto.authentication.AuthResponse;
import com.kenlhk.notekeeper.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {

    private final CommonMapper commonMapper;
    private final AuthenticationService authenticationService;

    public AuthResponse register(RegisterRequest request){
        User user = commonMapper.map(request, User.class);
        Map<String, String> credentials = authenticationService.register(user, request.getPassword2());
        return commonMapper.map(credentials, AuthResponse.class);
    }

    public AuthResponse login(LoginRequest request){
        User user = commonMapper.map(request, User.class);
        Map<String, String> credentials = authenticationService.login(user);
        return commonMapper.map(credentials, AuthResponse.class);
    }


}
