package com.stokkur.accounts.service;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> listAllAccounts() {
        return repository.findAll();
    }

    public Account addAccount(Account newAccount) {
        return repository.save(newAccount);
    }
}