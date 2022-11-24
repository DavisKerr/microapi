package com.davis.microapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.davis.microapi.model.User;
import com.davis.microapi.repository.UserRepository;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addNewUser(User user) {
        Optional<User> userOptional =  userRepository.findByUsername(user.getUsername());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("Username is taken");
        }
        userRepository.save(new User(
            user.getUsername(),
            passwordEncoder.encode(user.getPassword()),
            "ROLE_USER"
        ));
    }
}
