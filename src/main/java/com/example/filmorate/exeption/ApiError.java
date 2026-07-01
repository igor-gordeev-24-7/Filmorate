package com.example.filmorate.exeption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApiError {

    private String message;
    private List<String> errors;

    public ApiError(String message) {
        this.message = message;
        this.errors = new ArrayList<>();
    }

    public void addError(String error){
        errors.add(error);
    }
}
