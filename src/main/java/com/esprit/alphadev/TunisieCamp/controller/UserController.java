package com.esprit.alphadev.TunisieCamp.controller;


import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = "*", origins="*")
public class UserController extends BasicController{
    @Autowired
    UserService userService ;

    @PostMapping
    public User CreateUser(@Valid @RequestBody User user){

        return userService.save(user);
    }
}
