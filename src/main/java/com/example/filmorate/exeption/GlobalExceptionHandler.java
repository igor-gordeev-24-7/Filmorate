package com.example.filmorate.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//    @ExceptionHandler({RuntimeException.class})
//    protected ResponseEntity<String> handleRuntimeEx(RuntimeException runtimeException, WebRequest webRequest) {
//        return new ResponseEntity<>("Все пропало", HttpStatus.NOT_FOUND);
//    }
}
