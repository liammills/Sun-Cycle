package com.SunCycle.SunCycle.dto;

public class SimpleUserDTO {
    private int userId;
    private String email;
    private String username;

    public SimpleUserDTO(int userId, String email, String username) {
        this.userId = userId;
        this.email = email;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
