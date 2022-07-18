package com.stokkur.accounts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.stokkur.accounts.model.Account;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountResponse {
    @JsonInclude(Include.NON_NULL)
    private UUID id;
    private String username;
    private String password;

    public AccountResponse() {
    }

    private AccountResponse(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = "***********";
    }

    public static AccountResponse fromAccount(Account account) {
        return new AccountResponse(account);
    }

    public String getUsername() {
        return username;
    }

    public UUID getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
