package com.stokkur.accounts.controller;

import com.stokkur.accounts.exception.NoSuchEntityException;
import com.stokkur.accounts.response.ErrorResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class RestExceptionHandlerTest {

    @Test
    void shouldHandleNoSuchEntityExceptions() {
        RestExceptionHandler handler = new RestExceptionHandler();
        NoSuchEntityException exception = new NoSuchEntityException(RandomStringUtils.randomAlphanumeric(20));
        ResponseEntity<ErrorResponse> response = handler.handleNoSuchEntityException(exception);
        assertThat(response.getBody().getMessage()).isEqualTo(exception.getReason());
        assertThat(response.getStatusCode()).isEqualTo(exception.getStatus());
    }
}