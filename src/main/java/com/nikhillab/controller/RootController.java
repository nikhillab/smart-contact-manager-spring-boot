package com.nikhillab.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.nikhillab.entities.User;
import com.nikhillab.service.UserService;

@ControllerAdvice
public class RootController {
    
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if (authentication == null) {
            return;
        }
        String username = authentication.getName();
        logger.info("User logged in: {}", username);
        User user = userService.getUserByEmail(username);
        model.addAttribute("loggedInUser", user);

    }
}
