package com.workintech.s19d1.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(ApiException exception) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage(),
                exception.getHttpStatus().value(),
                LocalDateTime.now());
        log.error("*❗* ERROR LOG *❗* An ApiException has occurred: " + exception);
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleException(Exception exception) {
        ExceptionResponse response = new ExceptionResponse(exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now());
        log.error("*❗* ERROR LOG *❗* An unexpected ApiException has occurred: " + exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
