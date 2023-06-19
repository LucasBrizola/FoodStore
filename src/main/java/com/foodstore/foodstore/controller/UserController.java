package com.foodstore.foodstore.controller;

import com.foodstore.foodstore.domain.User;
import com.foodstore.foodstore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login/auth")
    @ResponseStatus(HttpStatus.OK)
    public User login(Authentication authentication) {
        User clientes = userService.findByEmail(authentication.getName());
        if (nonNull(clientes)) {
            return clientes;
        } else {
            return null;
        }
    }
}
