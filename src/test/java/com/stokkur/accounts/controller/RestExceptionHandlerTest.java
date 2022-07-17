package com.stokkur.accounts.controller;

import com.stokkur.accounts.exception.NoSuchEntityException;
import com.stokkur.accounts.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RestExceptionHandlerTest {

    @Test
    void shouldHandleNoSuchEntityExceptions() {
        RestExceptionHandler handler = new RestExceptionHandler();
        NoSuchEntityException exception = mock(NoSuchEntityException.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        ResponseEntity<ErrorResponse> response = handler.handleNoSuchEntityException(exception, request);
        assertThat(response.getBody().getMessage()).isEqualTo("Not Found");
    }
}