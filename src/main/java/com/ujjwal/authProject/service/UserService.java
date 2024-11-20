package com.ujjwal.authProject.service;

import com.ujjwal.authProject.Jwt.JWTService;
import com.ujjwal.authProject.model.Users;
import com.ujjwal.authProject.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtservice;

    @Autowired
    private AuthenticationManager authManager;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }


    //uses authManager to authenticate the user
    public String verify(Users user) {
        Authentication authentication  =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtservice.generateToken(user.getUsername());
        }
        else return "failed";

    }
}
