package com.stokkur.accounts.service;

import com.stokkur.accounts.exception.NoSuchEntityException;
import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.repository.AccountRepository;
import com.stokkur.accounts.request.AccountRequest;
import com.stokkur.accounts.util.AccountBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        Account sampleAccount = new AccountBuilder().build();
        List<Account> expectedAccounts = List.of(sampleAccount);
        when(repository.findAll()).thenReturn(expectedAccounts);
        assertThat(service.listAllAccounts()).containsAll(expectedAccounts);
    }

    @Test
    void shouldInsertNewAccountEntity() {
        AccountRequest sampleRequest = mock(AccountRequest.class);
        String randomName = RandomStringUtils.randomAlphabetic(10);
        Account sampleAccount = new AccountBuilder().withUsername(randomName).build();
        Account repositoryAccount = new AccountBuilder().withId(UUID.randomUUID()).withUsername(randomName).build();
        when(sampleRequest.toAccount()).thenReturn(sampleAccount);
        when(repository.save(sampleAccount)).thenReturn(repositoryAccount);
        assertThat(service.addAccount(sampleRequest)).isEqualTo(repositoryAccount);
    }

    @Test
    void shouldFindExistingIndividualAccount() {
        UUID id = UUID.randomUUID();
        Account sampleAccount = new AccountBuilder().withId(id).build();
        when(repository.findById(id)).thenReturn(Optional.of(sampleAccount));
        assertThat(service.fetchAccount(id)).isEqualTo(sampleAccount);
    }

    @Test
    void shouldThrowExceptionWhenAccountDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThatExceptionOfType(NoSuchEntityException.class)
                .isThrownBy(() -> service.fetchAccount(id))
                .withMessageContainingAll(String.format("Account %s not found", id), "404 NOT_FOUND");
    }

    @Test
    void shouldUpdateAccountGivenIdAndUpdateEntity() {
        UUID id = UUID.randomUUID();
        AccountRequest updatedAccount = new AccountBuilder().withUsername("joe").buildRequest();
        Account account = updatedAccount.toAccount();
        Account accountToUpdate = new AccountBuilder().withId(id).withUsername("David").build();
        when(repository.save(any(Account.class))).thenReturn(account);
        when(repository.findById(id)).thenReturn(Optional.of(accountToUpdate));
        assertThat(service.updateAccount(id, updatedAccount)).usingRecursiveComparison().isEqualTo(account);
    }

    @Test
    void shouldDeleteAccountGivenId() {
        UUID id = UUID.randomUUID();
        Account account = new AccountBuilder().withId(id).build();
        when(repository.findById(id)).thenReturn((Optional.of(account)));
        service.deleteAccount(id);
        verify(repository).deleteById(id);
    }
}
