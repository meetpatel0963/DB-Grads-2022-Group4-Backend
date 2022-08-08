package com.db.grad.javaapi.controller;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.constants.URIConstants;
import com.db.grad.javaapi.exception.BadRequestException;
import com.db.grad.javaapi.model.payload.ApiResponse;
import com.db.grad.javaapi.model.payload.JwtAuthenticationResponse;
import com.db.grad.javaapi.model.payload.LoginRequest;
import com.db.grad.javaapi.model.payload.SignUpRequest;
import com.db.grad.javaapi.model.user.User;
import com.db.grad.javaapi.repository.UserRepository;
import com.db.grad.javaapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(URIConstants.API_V1)
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping(URIConstants.SIGN_IN)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws BadRequestException {
        String jwt = authService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(URIConstants.SIGN_UP)
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, MessageConstants.USERNAME_ALREADY_IN_USE),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, MessageConstants.EMAIL_ALREADY_IN_USE),
                    HttpStatus.BAD_REQUEST);
        }

        User user = authService.registerUser(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getRoles(), signUpRequest.getPassword());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(URIConstants.API_V1 + URIConstants.GET_USER_PROFILE)
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, MessageConstants.USER_REGISTER_SUCCESS));
    }
}


