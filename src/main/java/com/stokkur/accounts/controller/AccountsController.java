package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.listAllAccounts();
    }

    @PostMapping("/new-account")
    public Account newAccount(Account account) {
        return accountService.addAccount(account);
    }
}
