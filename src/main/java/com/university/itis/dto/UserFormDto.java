package com.university.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.dto.common.AbstractDto;
import com.university.itis.model.User;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserFormDto extends AbstractDto {

    private String email;
    private String password;
    private String role;
    private String token;

    public UserFormDto() {
    }

    public UserFormDto(User user) {
        super(user);
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole().name();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

}
