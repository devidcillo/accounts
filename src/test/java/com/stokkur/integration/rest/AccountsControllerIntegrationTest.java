package com.stokkur.integration.rest;

import com.stokkur.accounts.AccountsApplication;
import com.stokkur.accounts.response.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.transaction.Transactional;
import java.util.UUID;

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
    @Sql("/drop_table_and_insert_five_accounts.sql")
    void shouldListExistingAccounts() {
        client.get().uri("/accounts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AccountResponse.class).hasSize(5);
    }

    @Test
    void shouldAddNewAccountGivenPayload() {
        client.post().uri("/new-account")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"name\": \"David\"}")
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AccountResponse.class).consumeWith(account -> {
                    assertThat(account.getResponseBody().getId()).isNotNull();
                    assertThat(account.getResponseBody().getUsername()).isEqualTo("David");
                });
    }

    @Test
    @Sql("/drop_table_and_insert_five_accounts.sql")
    void shouldGetIndividualAccount() {
        UUID id = UUID.fromString("cd8c8dc0-ae89-4ae7-b9f9-56812461faf8");
        client.get().uri("/accounts/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AccountResponse.class).consumeWith(account -> {
                    assertThat(account.getResponseBody().getId()).isEqualTo(id);
                    assertThat(account.getResponseBody().getUsername()).isEqualTo("Majo");
                });
    }

    @Test
    @Sql("/drop_table_and_insert_five_accounts.sql")
    void shouldEditExistingAccountGivenPayload() {
        UUID id = UUID.fromString("cd8c8dc0-ae89-4ae7-b9f9-56812461faf8");
        client.put().uri("/accounts/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"name\": \"David\"}")
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(AccountResponse.class).consumeWith(account -> {
                    assertThat(account.getResponseBody().getId()).isNotNull();
                    assertThat(account.getResponseBody().getUsername()).isEqualTo("David");
                });
    }

    @Test
    @Sql("/drop_table_and_insert_five_accounts.sql")
    void shouldDeleteExistingAccount() {
        UUID id = UUID.fromString("cd8c8dc0-ae89-4ae7-b9f9-56812461faf8");
        client.delete().uri("/accounts/{id}", id)
                .exchange()
                .expectStatus().isOk();

        client.get().uri("/accounts")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AccountResponse.class).hasSize(4);
    }
}
