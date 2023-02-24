package com.pfa.simplifyBack.controller;

import com.pfa.simplifyBack.model.User;
import com.pfa.simplifyBack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/neo4j/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping
    public Collection<User> getAll() {
        return userService.getAll();
    }

}
