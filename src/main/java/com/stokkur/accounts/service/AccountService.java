package com.stokkur.accounts.service;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.repository.AccountRepository;
import com.stokkur.accounts.request.AccountRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    private AccountRepository repository;

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
        return null;
    }
}
