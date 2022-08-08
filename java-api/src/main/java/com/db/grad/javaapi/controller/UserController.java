package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.payload.UserIdentityAvailability;
import com.db.grad.javaapi.model.payload.UserProfile;
import com.db.grad.javaapi.model.user.User;
import com.db.grad.javaapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(URIConstants.GET_USER_BY_ID)
    @PreAuthorize(MessageConstants.USER)
    public User getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return userService.getUserById(userId);
    }

    @GetMapping(URIConstants.CHECK_USERNAME_AVAILABILITY)
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping(URIConstants.CHECK_EMAIL_AVAILABILITY)
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        return userService.checkEmailAvailability(email);
    }

    @GetMapping(URIConstants.GET_USER_PROFILE)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        return userService.getUserProfile(username);
    }

}
