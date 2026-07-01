package com.example.filmorate.exeption;

import lombok.Getter;

import java.util.List;


@Getter
public class EntityNotFoundException extends RuntimeException {

    private final List<String> errors;

    public EntityNotFoundException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }


}
