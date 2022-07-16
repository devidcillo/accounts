package com.stokkur.accounts.service;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.repository.AccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Test
    void shouldFindAllExistingAccounts() {
        AccountRepository repository = mock(AccountRepository.class);
        AccountService service = new AccountService(repository);
        Account sampleAccount = new Account(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(10));
        List<Account> expectedAccounts = List.of(sampleAccount);
        when(repository.findAll()).thenReturn(expectedAccounts);
        assertThat(service.listAllAccounts()).containsAll(expectedAccounts);
    }

    @Test
    void shouldInsertNewAccountEntity() {
        AccountRepository repository = mock(AccountRepository.class);
        AccountService service = new AccountService(repository);
        Account sampleAccount = new Account(RandomUtils.nextLong(), RandomStringUtils.randomAlphabetic(10));
        when(repository.save(sampleAccount)).thenReturn(sampleAccount);
        assertThat(service.addAccount(sampleAccount)).isEqualTo(sampleAccount);
    }
}
