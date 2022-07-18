package com.stokkur.accounts.model;

import com.stokkur.accounts.util.AccountBuilder;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void shouldUpdateValuesFromTargetEntity() {
        UUID id = UUID.randomUUID();
        Account previousAccount = new AccountBuilder().withId(id).build();
        Account newValues = new AccountBuilder().build();
        Account updatedAccount = previousAccount.update(newValues);
        assertThat(updatedAccount.getId()).isEqualTo(previousAccount.getId());
        assertThat(updatedAccount.getUsername()).isEqualTo(updatedAccount.getUsername());
        assertThat(updatedAccount.getPassword()).isEqualTo(updatedAccount.getPassword());
    }
}

