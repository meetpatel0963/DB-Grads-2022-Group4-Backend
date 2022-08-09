package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.ErrorResponse;
import com.db.grad.javaapi.model.payload.UserIdentityAvailability;
import com.db.grad.javaapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        try {
            log.info("Fetch User By ID Request For UserID: {}", userId);
            return ResponseEntity.ok().body(userService.getUserById(userId));
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.GET_USER_BY_ID_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping(URIConstants.CHECK_USERNAME_AVAILABILITY)
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        log.info("Check Username Availability Request For Username: {}", username);
        return userService.checkUsernameAvailability(username);
    }

    @GetMapping(URIConstants.CHECK_EMAIL_AVAILABILITY)
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        log.info("Check Email Availability Request For Email: {}", email);
        return userService.checkEmailAvailability(email);
    }

    @GetMapping(URIConstants.GET_USER_PROFILE)
    @PreAuthorize(MessageConstants.USER_ADMIN)
    public ResponseEntity<?> getUserProfile(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        try {
            log.info("Fetch User Profile Request For Username: {}", username);
            return ResponseEntity.ok().body(userService.getUserProfile(username));
        }
        catch (ResourceNotFoundException E) {
            log.error(MessageConstants.GET_USER_PROFILE_BY_USERNAME_FAILED + E.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder().errorCode(HttpStatus.NOT_FOUND.value()).message(E.getMessage()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

}
