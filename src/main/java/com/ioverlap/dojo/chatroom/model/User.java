package com.ioverlap.dojo.chatroom.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class User {

    @NotBlank
    @Length(min = 3, max = 12)
    private String username;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
