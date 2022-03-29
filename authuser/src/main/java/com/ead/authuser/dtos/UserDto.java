package com.ead.authuser.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface UserView{
        public static interface RegistrantionPost{}
        public static interface UserPut{}
        public static interface PasswordPut{}
        public static interface ImagePut{}
    }

    @JsonView(UserView.RegistrantionPost.class)
    private String name;

    @JsonView(UserView.RegistrantionPost.class)
    private String email;

    @JsonView({UserView.RegistrantionPost.class, UserView.PasswordPut.class})
    private String password;

    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrantionPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrantionPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @JsonView(UserView.RegistrantionPost.class)
    private String cpf;

    @JsonView(UserView.ImagePut.class)
    private String imgUrl;

    public UserDto(){
    }

    public UserDto(String name, String email, String password, String oldPassword, String fullName, String phoneNumber, String cpf,
            String imgUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.oldPassword = oldPassword;
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

    public String getOldPassword(){
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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