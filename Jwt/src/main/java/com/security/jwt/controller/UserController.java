package com.security.jwt.controller;

import com.security.jwt.entity.User;
import com.security.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registernewuser")
    public User registerNewUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/foradmin")
//    @PreAuthorize("hasRole('Admin')")
    public String hiAdmin(){
        return "HI your are admin.............";
    }

    @GetMapping("/foruser")
    @PreAuthorize("hasRole('User')")
    public String hiUser(){
        return "HI your are user.............";
    }

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }
}
