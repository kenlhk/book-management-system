package com.kenlhk.notekeeper.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmailException extends RuntimeException{

    private final String message;
}
