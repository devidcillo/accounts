package com.stokkur.accounts.response;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.util.AccountBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AccountResponseTest {
    @Test
    void shouldCreateResponseFromAccountModel() {
        LocalDateTime fifteenthOfOctober2019 = LocalDateTime.of(2019, 10, 15, 0, 1);
        Account account = new AccountBuilder().withMemberSince(fifteenthOfOctober2019).build();
        AccountResponse actual = AccountResponse.fromAccount(account);
        assertThat(actual.getId()).isEqualTo(account.getId());
        assertThat(actual.getUsername()).isEqualTo(account.getUsername());
        assertThat(actual.getPassword()).isEqualTo("***********");
        assertThat(actual.getMemberSince()).isEqualTo("15-10-2019");
    }
}