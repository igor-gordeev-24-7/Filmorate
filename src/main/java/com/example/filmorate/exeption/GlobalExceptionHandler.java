package com.example.filmorate.exeption;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{


    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleValidationExceptions(EntityNotFoundException ex, WebRequest request) {

        ApiError error = new ApiError(ex.getMessage());
        ex.getErrors().forEach(error::addError);
        logger.debug(ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
