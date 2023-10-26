package com.SunCycle.SunCycle.dto;

import com.SunCycle.SunCycle.model.User;

public class LoginResponseDTO {
    private SimpleUserDTO user;
    private String jwt;

    private Status result;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(SimpleUserDTO user, String jwt, Status result){
        this.user = user;
        this.jwt = jwt;
        this.result = result;
    }

    public LoginResponseDTO(Status result) {
        this.result = result;
    }

    public SimpleUserDTO getUser(){
        return this.user;
    }

    public void setUser(SimpleUserDTO user){
        this.user = user;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

    public Status getStatus() {
        return this.result;
    }


}
