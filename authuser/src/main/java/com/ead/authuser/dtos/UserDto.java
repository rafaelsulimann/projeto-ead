package com.ead.authuser.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ead.authuser.validation.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonView;

public class UserDto implements Serializable{

    private static final long serialVersionUID = 1L;

    public interface UserView{
        public static interface RegistrantionPost{}
        public static interface UserPut{}
        public static interface PasswordPut{}
        public static interface ImagePut{}
    }

    @Size(min = 4, max = 50, groups = UserView.RegistrantionPost.class)
    @UsernameConstraint(groups = UserView.RegistrantionPost.class)   
    @JsonView(UserView.RegistrantionPost.class)
    private String userName;

    @NotBlank(groups = UserView.RegistrantionPost.class)
    @Email(groups = UserView.RegistrantionPost.class)
    @JsonView(UserView.RegistrantionPost.class)
    private String email;

    @NotBlank(groups = {UserView.RegistrantionPost.class, UserView.PasswordPut.class})
    @Size(min = 6, max = 20, groups = {UserView.RegistrantionPost.class, UserView.PasswordPut.class})
    @JsonView({UserView.RegistrantionPost.class, UserView.PasswordPut.class})
    private String password;

    @NotBlank(groups = UserView.PasswordPut.class)
    @Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
    @JsonView(UserView.PasswordPut.class)
    private String oldPassword;

    @JsonView({UserView.RegistrantionPost.class, UserView.UserPut.class})
    private String fullName;

    @Size(min = 9, max = 11, groups = UserView.UserPut.class)
    @JsonView({UserView.RegistrantionPost.class, UserView.UserPut.class})
    private String phoneNumber;

    @NotBlank(groups = UserView.RegistrantionPost.class)
    @Size(min = 11, max = 11, groups = UserView.RegistrantionPost.class)
    @JsonView(UserView.RegistrantionPost.class)
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView(UserView.ImagePut.class)
    private String imgUrl;

    public UserDto(){
    }

    public UserDto(String userName, String email, String password, String oldPassword, String fullName, String phoneNumber, String cpf,
            String imgUrl) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.oldPassword = oldPassword;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.imgUrl = imgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
