package com.stokkur.accounts.service;

import com.stokkur.accounts.exception.NoSuchEntityException;
import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.repository.AccountRepository;
import com.stokkur.accounts.request.AccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> listAllAccounts() {
        return repository.findAll();
    }

    public Account addAccount(AccountRequest accountRequest) {
        return repository.save(accountRequest.toAccount());
    }

    public Account fetchAccount(UUID id) {
        Optional<Account> accountOptional = repository.findById(id);
        if (accountOptional.isPresent())
            return accountOptional.get();
        throw new NoSuchEntityException(String.format("Account %s not found", id));
    }

    public Account updateAccount(UUID id, AccountRequest accountRequest) {
        Account previousAccount = fetchAccount(id);
        Account updatedValues = accountRequest.toAccount();
        return repository.save(previousAccount.update(updatedValues));
    }
}
