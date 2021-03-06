package com.stokkur.accounts.util;

import com.stokkur.accounts.model.Account;
import com.stokkur.accounts.request.AccountRequest;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccountBuilder {
    private UUID id = UUID.randomUUID();
    private String username = RandomStringUtils.randomAlphanumeric(10);
    private String password = RandomStringUtils.randomAlphanumeric(10);
    private LocalDateTime memberSince = LocalDateTime.now();

    public AccountBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public AccountBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public AccountBuilder withMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
        return this;
    }

    public Account build() {
        return new Account(id, username, password, memberSince);
    }

    public AccountRequest buildRequest() {
        return new AccountRequest(username, password);
    }
}
