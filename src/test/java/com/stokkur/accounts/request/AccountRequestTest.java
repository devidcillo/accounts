package com.stokkur.accounts.request;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.util.AccountBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRequestTest {
    @Test
    void shouldTransformRequestToAccountModel() {
        String username = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        AccountRequest request = new AccountBuilder().withUsername(username).withPassword(password).buildRequest();
        Account actual = request.toAccount();
        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getUsername()).isEqualTo(username);
        assertThat(actual.getPassword()).isEqualTo(password);
    }
}