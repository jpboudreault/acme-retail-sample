package com.acme.controller;

import com.acme.model.User;
import com.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("companies")
    public Iterable<User> users() {
        return userRepository.findAll();
    }
}
