package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.exception.BadRequestException;
import com.db.grad.javaapi.model.user.Role;
import com.db.grad.javaapi.model.user.User;
import com.db.grad.javaapi.repository.UserRepository;
import com.db.grad.javaapi.security.JwtTokenProvider;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    public String authenticateUser(@NonNull final String username, @NonNull final String password) throws BadRequestException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadRequestException(MessageConstants.INVALID_USERNAME_OR_PASSWORD));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return jwt;
    }

    public User registerUser(@NonNull final String firstName, @NonNull final String lastName,
                             @NonNull final String username, @NonNull final String email,
                             @NonNull final Set<Role> roles, @NonNull final String password) {
        // Creating user's account
        User user = new User(firstName + " " + lastName, username, email, password, roles, new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

}
