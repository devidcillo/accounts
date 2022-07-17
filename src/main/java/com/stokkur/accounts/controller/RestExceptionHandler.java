package com.stokkur.accounts.controller;

import com.stokkur.accounts.exception.NoSuchEntityException;
import com.stokkur.accounts.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchEntityException(NoSuchEntityException exception) {
        ErrorResponse response = new ErrorResponse(exception.getReason());
        return new ResponseEntity<>(response, exception.getStatus());
    }
}
