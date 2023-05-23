package com.esprit.alphadev.TunisieCamp.service;

import com.esprit.alphadev.TunisieCamp.entities.User;
import com.esprit.alphadev.TunisieCamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user){
        return userRepository.save(user);
    }
}
