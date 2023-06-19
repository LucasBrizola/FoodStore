package com.foodstore.foodstore.service;

import com.foodstore.foodstore.domain.User;
import com.foodstore.foodstore.repository.UserRepository;
import com.foodstore.foodstore.service.exception.User.UserMissingValueException;
import com.foodstore.foodstore.service.exception.User.UserNotFoundException;
import com.foodstore.foodstore.service.exception.User.UserRepeatedException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserRepeatedException(user.getEmail());
        }
        if (verifyFields(user)) {
            return this.userRepository.save(user);
        }
        return null;
    }

    private boolean verifyFields(User user) {
        if (user.getEmail() == null || user.getSenha() == null) {
            throw new UserMissingValueException();
        }
        return true;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
