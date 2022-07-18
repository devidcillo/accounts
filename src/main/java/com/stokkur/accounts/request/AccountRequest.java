package com.stokkur.accounts.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stokkur.accounts.model.Account;

public class AccountRequest {
    private final String username;
    private final String password;

    @JsonCreator
    public AccountRequest(
            @JsonProperty(value = "username", required = true) String username,
            @JsonProperty(value = "password", required = true) String password) {
        this.username = username;
        this.password = password;
    }

    public Account toAccount() {
        return new Account(username, password);
    }
}
