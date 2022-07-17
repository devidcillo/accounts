package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    public Account newAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }
}
