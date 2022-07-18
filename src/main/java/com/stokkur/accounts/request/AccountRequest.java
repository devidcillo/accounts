package com.stokkur.accounts.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stokkur.accounts.model.Account;

public class AccountRequest {
    private final String name;
    private final String password;

    @JsonCreator
    public AccountRequest(
            @JsonProperty("name") String name,
            @JsonProperty("password") String password) {
        this.name = name;
        this.password = password;
    }

    public Account toAccount() {
        return new Account(name, password);
    }
}
