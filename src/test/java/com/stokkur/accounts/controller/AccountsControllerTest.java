package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.request.AccountRequest;
import com.stokkur.accounts.response.AccountResponse;
import com.stokkur.accounts.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
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
        Account sampleAccount = new Account(RandomStringUtils.randomAlphabetic(10));
        List<Account> expectedAccounts = List.of(sampleAccount);
        when(accountService.listAllAccounts()).thenReturn(expectedAccounts);
        assertThat(controller.getAllAccounts()).containsAll(expectedAccounts);
    }

    @Test
    void shouldAddAccountGivenUserInformation() {
        AccountService service = mock(AccountService.class);
        AccountsController controller = new AccountsController(service);
        AccountRequest accountRequest = new AccountRequest(RandomStringUtils.randomAlphabetic(10));
        Account account = accountRequest.toAccount();
        when(service.addAccount(accountRequest)).thenReturn(account);
        assertThat(controller.newAccount(accountRequest)).usingRecursiveComparison().isEqualTo(AccountResponse.fromAccount(account));
    }
}
