package com.stokkur.accounts.controller;

import com.stokkur.accounts.exception.NoSuchEntityException;
import com.stokkur.accounts.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NoSuchEntityException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchEntityException(NoSuchEntityException exception, HttpServletRequest servletRequest) {
        ErrorResponse response = new ErrorResponse("Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
