package com.stokkur.accounts.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Account {
    @Id
    private UUID id;
    private String username;
    private String password;
    private LocalDateTime memberSince;

    public Account() {
    }

    public Account(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.memberSince = LocalDateTime.now();
    }

    public Account(UUID id, String username, String password, LocalDateTime memberSince) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.memberSince = memberSince;
    }

    public Account update(Account updatedAccount) {
        if (updatedAccount.username.isEmpty())
            return new Account(id, username, password, memberSince);
        else
            return new Account(id, updatedAccount.username, updatedAccount.password, memberSince);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getMemberSince() {
        return memberSince;
    }

    public void setMemberSince(LocalDateTime memberSince) {
        this.memberSince = memberSince;
    }
}
