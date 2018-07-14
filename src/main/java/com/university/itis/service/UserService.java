package com.university.itis.service;


import com.university.itis.dto.UserDto;
import com.university.itis.dto.UserFormDto;

import com.university.itis.model.Token;
import com.university.itis.model.User;

import java.util.List;

public interface UserService extends BaseService<UserDto> {

    public String getCurrentUserEmail();

    public User getCurrentUser();

    Boolean ping(Token token);

    UserFormDto authenticate(UserFormDto userFormDto);


    List<UserDto> getAll();
}
