package com.stokkur.accounts.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Account {
    @Id
    private UUID id;
    private String username;
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
    }

    public Account(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account update(Account updatedAccount) {
        if (updatedAccount.username.isEmpty())
            return new Account(id, username, password);
        else
            return new Account(id, updatedAccount.username, updatedAccount.password);
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
}
