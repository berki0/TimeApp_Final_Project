package com.example.TimeApp.Entities;

public enum Role {
    USER("user"),
    ADMIN("admin");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Role(String name){
        this.name=name;

    }
}
