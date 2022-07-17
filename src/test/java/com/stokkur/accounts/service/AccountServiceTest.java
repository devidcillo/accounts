package com.stokkur.accounts.service;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.repository.AccountRepository;
import com.stokkur.accounts.request.AccountRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private AccountRepository repository;
    private AccountService service;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepository.class);
        service = new AccountService(repository);
    }

    @Test
    void shouldFindAllExistingAccounts() {
        Account sampleAccount = new Account(RandomStringUtils.randomAlphabetic(10));
        List<Account> expectedAccounts = List.of(sampleAccount);
        when(repository.findAll()).thenReturn(expectedAccounts);
        assertThat(service.listAllAccounts()).containsAll(expectedAccounts);
    }

    @Test
    void shouldInsertNewAccountEntity() {
        AccountRequest sampleRequest = mock(AccountRequest.class);
        String randomName = RandomStringUtils.randomAlphabetic(10);
        Account sampleAccount = new Account(randomName);
        Account repositoryAccount = new Account(RandomUtils.nextLong(), randomName);
        when(sampleRequest.toAccount()).thenReturn(sampleAccount);
        when(repository.save(sampleAccount)).thenReturn(repositoryAccount);
        assertThat(service.addAccount(sampleRequest)).isEqualTo(repositoryAccount);
    }
}
