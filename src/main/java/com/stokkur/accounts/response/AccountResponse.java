package com.stokkur.accounts.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.stokkur.accounts.model.Account;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountResponse {
    @JsonInclude(Include.NON_NULL)
    private UUID id;
    @JsonInclude(Include.NON_NULL)
    private String username;
    @JsonInclude(Include.NON_NULL)
    private String password;
    @JsonInclude(Include.NON_NULL)
    private String memberSince;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public AccountResponse() {
    }

    private AccountResponse(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = "***********";
        this.memberSince = account.getMemberSince().format(dateFormatter);
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

    public String getMemberSince() {
        return memberSince;
    }
}
