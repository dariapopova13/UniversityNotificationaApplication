package com.university.itis.controller;


import com.university.itis.dto.UserDto;
import com.university.itis.model.Token;
import com.university.itis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @ResponseBody
    @PostMapping(value = "ping")
    public Boolean ping(@RequestBody Token token) {
        return userService.ping(token);
    }

    @PostMapping(value = "save")
    public UserDto save(@RequestBody UserDto user) {
        return userService.saveOrUdpate(user);
    }

    @GetMapping(value = "/")
    public List<UserDto> getAllUsers(){
        return userService.getAll();
    }
}