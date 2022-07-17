package com.stokkur.accounts.response;

import com.stokkur.accounts.model.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccountResponseTest {
    @Test
    void shouldCreateResponseFromAccountModel() {
        Account account = new Account(UUID.randomUUID(), RandomStringUtils.randomAlphabetic(10));
        AccountResponse actual = AccountResponse.fromAccount(account);
        assertThat(actual.getId()).isEqualTo(account.getId());
        assertThat(actual.getName()).isEqualTo(account.getName());
    }
}