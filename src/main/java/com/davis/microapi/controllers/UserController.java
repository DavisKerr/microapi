package com.davis.microapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.davis.microapi.model.User;
import com.davis.microapi.repository.UserRepository;
import com.davis.microapi.service.UserService;

import antlr.collections.List;

@RestController
@RequestMapping(path = "/register")
public class UserController {
    
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
	public String getUser() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User me = userRepository.findByUsername(currentPrincipalName).orElseThrow(() -> new Exception("Error"));
        return me.toString();
	}

    @PostMapping
    public void registerNewUser(@RequestBody User user) throws Exception{
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher usernameMatcher = usernamePattern.matcher(user.getUsername());
        boolean usernameMatchFound = usernameMatcher.find();

        Pattern passwordPattern = Pattern.compile("^[!@#$%?&*{}^/<>,._a-zA-Z0-9]+$");
        Matcher passwordMatcher = usernamePattern.matcher(user.getPassword());
        boolean passwordMatchFound = passwordMatcher.find();
        if(usernameMatchFound 
        && user.getUsername().length() > 0 
        && passwordMatchFound
        && user.getPassword().length() >= 7
        )
        {
            userService.addNewUser(user);
        }
        else {
            throw new Exception("Username or password do not meet criteria.");
        }
        
    }
}
