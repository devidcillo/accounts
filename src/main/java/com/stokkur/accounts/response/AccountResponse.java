package com.stokkur.accounts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.stokkur.accounts.model.Account;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountResponse {
    @JsonInclude(Include.NON_NULL)
    private Long id;
    private String name;

    public AccountResponse() {
    }

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
