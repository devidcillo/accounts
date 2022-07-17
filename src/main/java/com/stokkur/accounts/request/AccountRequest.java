package com.stokkur.accounts.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stokkur.accounts.model.Account;

public class AccountRequest {
    private final String name;

    @JsonCreator
    public AccountRequest(
            @JsonProperty("name") String name
    ) {
        this.name = name;
    }

    public Account toAccount() {
        return new Account(name);
    }
}
