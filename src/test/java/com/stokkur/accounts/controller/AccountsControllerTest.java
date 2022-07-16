package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountsControllerTest {

    @Test
    void shouldListAllAccounts() {
        AccountService accountService = mock(AccountService.class);
        AccountsController controller = new AccountsController(accountService);
        Account sampleAccount = new Account(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(10));
        List<Account> expectedAccounts = List.of(sampleAccount);
        when(accountService.listAllAccounts()).thenReturn(expectedAccounts);
        assertThat(controller.getAllAccounts()).containsAll(expectedAccounts);
    }
}
