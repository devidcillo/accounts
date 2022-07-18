package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.request.AccountRequest;
import com.stokkur.accounts.response.AccountResponse;
import com.stokkur.accounts.service.AccountService;
import com.stokkur.accounts.util.AccountBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountsControllerTest {

    private AccountService service;
    private AccountsController controller;
    private AccountRequest accountRequest;
    private Account sampleAccount;
    private AccountResponse expectedAccountResponse;

    @BeforeEach
    void setUp() {
        sampleAccount = new AccountBuilder().build();
        accountRequest = new AccountBuilder().buildRequest();
        expectedAccountResponse = AccountResponse.fromAccount(sampleAccount);
        service = mock(AccountService.class);
        controller = new AccountsController(service);
    }

    @Test
    void shouldListAllAccounts() {
        List<Account> accountList = List.of(sampleAccount);
        List<AccountResponse> responseList = List.of(expectedAccountResponse);
        when(service.listAllAccounts()).thenReturn(accountList);
        assertThat(controller.getAllAccounts()).usingRecursiveComparison().ignoringFieldsOfTypes(DateTimeFormatter.class).isEqualTo(responseList);
    }

    @Test
    void shouldAddAccountGivenUserInformation() {
        when(service.addAccount(accountRequest)).thenReturn(sampleAccount);
        assertThat(controller.newAccount(accountRequest)).usingRecursiveComparison()
                .ignoringFieldsOfTypes(DateTimeFormatter.class).isEqualTo(expectedAccountResponse);
    }

    @Test
    void shouldFetchAccountGivenId() {
        UUID id = UUID.randomUUID();
        when(service.fetchAccount(id)).thenReturn(sampleAccount);
        assertThat(controller.getAccount(id)).usingRecursiveComparison()
                .ignoringFieldsOfTypes(DateTimeFormatter.class).isEqualTo(expectedAccountResponse);
    }

    @Test
    void shouldUpdateAccountGivenId() {
        UUID id = UUID.randomUUID();
        when(service.updateAccount(id, accountRequest)).thenReturn(sampleAccount);
        assertThat(controller.updateAccount(id, accountRequest)).usingRecursiveComparison()
                .ignoringFieldsOfTypes(DateTimeFormatter.class).isEqualTo(expectedAccountResponse);
    }

    @Test
    void shouldDeleteAccountGivenId() {
        UUID id = UUID.randomUUID();
        controller.deleteAccount(id);
        verify(service).deleteAccount(id);
    }
}
