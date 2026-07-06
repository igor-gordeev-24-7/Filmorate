package com.example.filmorate.exeption;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                            HttpHeaders headers,
                                                                            HttpStatusCode status,
                                                                            WebRequest request) {
        ApiError error = new ApiError(ex.getMessage());
        error.addError("Некорректные аргументы запроса");
        logger.debug(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ApiError error = new ApiError("Некорректный запрос");
        error.addError(ex.getMessage());
        logger.debug(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
