package com.university.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.dto.common.AbstractDto;
import com.university.itis.model.User;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto extends AbstractDto {

    private String name;
    private String surname;
    private String role;
    private String password;
    private String email;

    public UserDto(User user) {
        super(user);
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole().name();
        this.email = user.getEmail();
    }

    public UserDto() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
