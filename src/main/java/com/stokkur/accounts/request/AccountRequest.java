package com.stokkur.accounts.request;

import com.stokkur.accounts.model.Account;

public class AccountRequest {
    private final String name;

    public AccountRequest(String name) {
        this.name = name;
    }

    public Account toAccount() {
        return new Account(name);
    }
}
