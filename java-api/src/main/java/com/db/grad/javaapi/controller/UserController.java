package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.payload.UserIdentityAvailability;
import com.db.grad.javaapi.model.payload.UserProfile;
import com.db.grad.javaapi.model.user.User;
import com.db.grad.javaapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(URIConstants.API_V1)
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return userService.getUserById(userId);
    }

    @GetMapping("/users/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping("/users/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.checkEmailAvailability(email);
    }

    @GetMapping("/users/username/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        return userService.getUserProfile(username);
    }

}
