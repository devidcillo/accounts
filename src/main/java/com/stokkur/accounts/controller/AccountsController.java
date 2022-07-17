package com.stokkur.accounts.controller;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.request.AccountRequest;
import com.stokkur.accounts.response.AccountResponse;
import com.stokkur.accounts.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<AccountResponse> getAllAccounts() {
        Stream<Account> accountStream = accountService.listAllAccounts().stream();
        return accountStream.map(AccountResponse::fromAccount).collect(Collectors.toList());
    }

    @PostMapping("/new-account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponse newAccount(@RequestBody AccountRequest account) {
        return AccountResponse.fromAccount(accountService.addAccount(account));
    }

    @GetMapping("/accounts/{id}")
    public AccountResponse getAccount(@PathVariable UUID id) {
        return AccountResponse.fromAccount(accountService.fetchAccount(id));
    }

    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponse updateAccount(@PathVariable UUID id, @RequestBody AccountRequest account) {
        return AccountResponse.fromAccount(accountService.updateAccount(id, account));
    }
}
