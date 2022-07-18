package com.stokkur.accounts.response;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.util.AccountBuilder;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountResponseTest {
    @Test
    void shouldCreateResponseFromAccountModel() {
        Account account = new AccountBuilder().build();
        AccountResponse actual = AccountResponse.fromAccount(account);
        assertThat(actual.getId()).isEqualTo(account.getId());
        assertThat(actual.getUsername()).isEqualTo(account.getUsername());
        assertThat(actual.getPassword()).isEqualTo("***********");
    }
}