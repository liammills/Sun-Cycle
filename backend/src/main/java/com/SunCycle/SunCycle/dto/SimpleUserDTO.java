package com.SunCycle.SunCycle.dto;

import java.util.Date;

public class SimpleUserDTO {
    private int userId;
    private String email;
    private String username;
    private Date expirationDate;

    public SimpleUserDTO(int userId, String email, String username, Date expirationDate) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.expirationDate = expirationDate;
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
