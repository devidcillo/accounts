package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.request.AccountRequest;
import com.stokkur.accounts.response.AccountResponse;
import com.stokkur.accounts.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountsControllerTest {

    private AccountService service;
    private AccountsController controller;
    private AccountRequest accountRequest;
    private Account sampleAccount;
    private AccountResponse expectedAccountResponse;

    @BeforeEach
    void setUp() {
        sampleAccount = new Account(RandomStringUtils.randomAlphabetic(10));
        accountRequest = new AccountRequest(RandomStringUtils.randomAlphabetic(10));
        expectedAccountResponse = AccountResponse.fromAccount(sampleAccount);
        service = mock(AccountService.class);
        controller = new AccountsController(service);
    }

    @Test
    void shouldListAllAccounts() {
        List<Account> accountList = List.of(sampleAccount);
        List<AccountResponse> responseList = List.of(expectedAccountResponse);
        when(service.listAllAccounts()).thenReturn(accountList);
        assertThat(controller.getAllAccounts()).usingRecursiveComparison().isEqualTo(responseList);
    }

    @Test
    void shouldAddAccountGivenUserInformation() {
        when(service.addAccount(accountRequest)).thenReturn(sampleAccount);
        assertThat(controller.newAccount(accountRequest)).usingRecursiveComparison().isEqualTo(expectedAccountResponse);
    }

    @Test
    void shouldFetchAccountGivenId() {
        UUID id = UUID.randomUUID();
        when(service.fetchAccount(id)).thenReturn(sampleAccount);
        assertThat(controller.getAccount(id)).usingRecursiveComparison().isEqualTo(expectedAccountResponse);
    }

    @Test
    void shouldUpdateAccountGivenId() {
        UUID id = UUID.randomUUID();
        when(service.updateAccount(id, accountRequest)).thenReturn(sampleAccount);
        assertThat(controller.updateAccount(id, accountRequest)).usingRecursiveComparison().isEqualTo(expectedAccountResponse);
    }
}
