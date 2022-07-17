package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.request.AccountRequest;
import com.stokkur.accounts.response.AccountResponse;
import com.stokkur.accounts.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountsControllerTest {

    private AccountService service;
    private AccountsController controller;

    @BeforeEach
    void setUp() {
        service = mock(AccountService.class);
        controller = new AccountsController(service);
    }

    @Test
    void shouldListAllAccounts() {
        Account sampleAccount = new Account(RandomStringUtils.randomAlphabetic(10));
        List<Account> accountList = List.of(sampleAccount);
        List<AccountResponse> responseList = List.of(AccountResponse.fromAccount(sampleAccount));
        when(service.listAllAccounts()).thenReturn(accountList);
        assertThat(controller.getAllAccounts()).usingRecursiveComparison().isEqualTo(responseList);
    }

    @Test
    void shouldAddAccountGivenUserInformation() {
        AccountRequest accountRequest = new AccountRequest(RandomStringUtils.randomAlphabetic(10));
        Account account = accountRequest.toAccount();
        when(service.addAccount(accountRequest)).thenReturn(account);
        assertThat(controller.newAccount(accountRequest)).usingRecursiveComparison().isEqualTo(AccountResponse.fromAccount(account));
    }
}
