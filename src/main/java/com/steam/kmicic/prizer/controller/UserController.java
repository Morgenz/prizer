package com.steam.kmicic.prizer.controller;

import com.steam.kmicic.prizer.domain.User;
import com.steam.kmicic.prizer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public void register(@Valid @RequestBody User user) {
        userRepository.save(user);
    }

}
