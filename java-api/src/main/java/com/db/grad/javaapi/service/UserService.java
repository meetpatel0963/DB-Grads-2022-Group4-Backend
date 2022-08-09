package com.db.grad.javaapi.service;

import com.db.grad.javaapi.constants.MessageConstants;
import com.db.grad.javaapi.exception.ResourceNotFoundException;
import com.db.grad.javaapi.model.payload.UserIdentityAvailability;
import com.db.grad.javaapi.model.payload.UserProfile;
import com.db.grad.javaapi.model.payload.UserSummary;
import com.db.grad.javaapi.model.user.User;
import com.db.grad.javaapi.repository.UserRepository;
import com.db.grad.javaapi.security.CurrentUser;
import com.db.grad.javaapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail());
        return userSummary;
    }

    public User getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_USER_FOUND_FOR_GIVEN_USER_ID));
    }

    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    public UserProfile getUserProfile(@PathVariable(value = "username") String username) throws ResourceNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException(MessageConstants.NO_USER_FOUND_FOR_GIVEN_USERNAME));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(),
                user.getEmail(), user.getCreationDate());

        return userProfile;
    }

}
