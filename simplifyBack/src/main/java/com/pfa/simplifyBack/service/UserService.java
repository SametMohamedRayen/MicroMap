package com.pfa.simplifyBack.service;

import com.pfa.simplifyBack.model.User;
import com.pfa.simplifyBack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Collection<User> getAll() {
        return userRepository.findAll();
    }
}
