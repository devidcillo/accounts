package com.stokkur.accounts.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Account {
    @Id
    private UUID id;
    private String name;

    public Account() {
    }

    public Account(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Account(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
