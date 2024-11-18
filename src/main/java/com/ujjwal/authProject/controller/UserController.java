package com.ujjwal.authProject.controller;

import com.ujjwal.authProject.model.Users;
import com.ujjwal.authProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users reqister(@RequestBody Users user){
        return service.register(user);
    }



}
