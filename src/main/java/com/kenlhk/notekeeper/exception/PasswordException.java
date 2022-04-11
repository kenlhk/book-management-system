package com.kenlhk.notekeeper.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordException extends RuntimeException{

    private final String passwordError;
}
