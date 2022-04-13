package com.kenlhk.notekeeper.service;

import com.kenlhk.notekeeper.domain.User;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> register(User user, String password2);

    Map<String, String> login(User user);

    User findCurrentUser();

    Boolean isCurrentUser(User user);
}
