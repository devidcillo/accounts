package com.stokkur.integration.rest;

import com.stokkur.accounts.AccountsApplication;
import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.response.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = AccountsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AccountsControllerIntegrationTest {
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    void shouldAddNewAccountGivenPayload() {
        client.post().uri("/new-account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"name\": \"David\"}")
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AccountResponse.class)
                .consumeWith(account -> {
                    assertThat(account.getResponseBody().getId()).isPositive();
                    assertThat(account.getResponseBody().getName()).isEqualTo("David");
                });
    }
}
