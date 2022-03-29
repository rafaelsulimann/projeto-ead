package com.ead.authuser.dtos;

import java.io.Serializable;

public class UserDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String cpf;
    private String imgUrl;

    public UserDto(){
    }

    public UserDto(String name, String email, String password, String fullName, String phoneNumber, String cpf,
            String imgUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
}
