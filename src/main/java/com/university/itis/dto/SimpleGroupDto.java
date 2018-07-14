package com.university.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.university.itis.dto.common.AbstractDto;
import com.university.itis.model.Group;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleGroupDto extends AbstractDto {

    private String name;
    private UserDto user;
    private String info;
    private UserDto admin;

    public SimpleGroupDto(Group group) {
        super(group);
        this.name = group.getName();
        this.info = group.getInfo();
        if (group.getUser() != null)
            this.user = new UserDto(group.getUser());
        if (group.getAdmin() != null)
            this.admin = new UserDto(group.getAdmin());

    }

    public SimpleGroupDto() {
    }

    public UserDto getAdmin() {
        return admin;
    }

    public void setAdmin(UserDto admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
