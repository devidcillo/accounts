package com.stokkur.accounts.model;

import com.stokkur.accounts.util.AccountBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountTest {

    @Test
    void shouldUpdateValuesFromTargetEntity() {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        Account previousAccount = new AccountBuilder().withId(id).withMemberSince(now).build();
        Account newValues = new AccountBuilder().build();
        Account updatedAccount = previousAccount.update(newValues);
        assertThat(updatedAccount.getId()).isEqualTo(previousAccount.getId());
        assertThat(updatedAccount.getUsername()).isEqualTo(updatedAccount.getUsername());
        assertThat(updatedAccount.getPassword()).isEqualTo(updatedAccount.getPassword());
        assertThat(updatedAccount.getMemberSince()).isEqualTo(now);
    }
}

