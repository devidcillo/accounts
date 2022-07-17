package com.stokkur.accounts.response;

import com.stokkur.accounts.model.Account;

public class AccountResponse {
    private final String name;
    private final Long id;

    private AccountResponse(Account account) {
        this.id = account.getId();
        this.name = account.getName();
    }

    public static AccountResponse fromAccount(Account account) {
        return new AccountResponse(account);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
