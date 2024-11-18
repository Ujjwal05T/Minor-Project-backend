package com.ujjwal.authProject.controller;

import com.ujjwal.authProject.model.Users;
import com.ujjwal.authProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users reqister(@RequestBody Users user){
        return service.register(user);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }


}
