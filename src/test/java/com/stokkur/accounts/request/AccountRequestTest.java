package com.stokkur.accounts.request;

import com.stokkur.accounts.model.Account;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountRequestTest {
    @Test
    void shouldTransformRequestToAccountModel() {
        String name = RandomStringUtils.randomAlphabetic(10);
        AccountRequest request = new AccountRequest(name);
        Account actual = request.toAccount();
        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo(name);
    }
}