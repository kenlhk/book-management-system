package com.kenlhk.notekeeper.service.impl;

import com.kenlhk.notekeeper.model.User;
import com.kenlhk.notekeeper.repository.UserRepository;
import com.kenlhk.notekeeper.security.JwtProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static com.kenlhk.notekeeper.util.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@Rollback(false)
class AuthenticationServiceImplTest {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    public void register() {
        User user = new User();
        user.setUsername(USERNAME1);
        user.setPassword(PASSWORD1);
        user.setEmail(EMAIL1);

        assertNotNull(authenticationService.register(user, PASSWORD1));
        verify(userRepository, times(1)).save(user);
        verify(jwtProvider, times(1)).generateToken(user);
    }

    @Test
    void login() {
        User user = new User();
        user.setUsername(USERNAME1);
        user.setPassword(PASSWORD1);

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        assertNotNull(authenticationService.login(user));
        verify(jwtProvider, times(1)).generateToken(user);
    }
}