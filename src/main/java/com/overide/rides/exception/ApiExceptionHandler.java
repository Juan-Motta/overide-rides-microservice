package com.overide.rides.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    /** ApiExceptionHandler */

    @ExceptionHandler(value = { ApiRequestException.class })
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException e) {
        // payload containing exception details
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("GMT-5")));
        // return response entity
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
