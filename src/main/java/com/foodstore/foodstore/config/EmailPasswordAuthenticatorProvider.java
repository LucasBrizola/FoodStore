package com.foodstore.foodstore.config;

import com.foodstore.foodstore.domain.User;
import com.foodstore.foodstore.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static java.util.Objects.nonNull;

@Component
public class EmailPasswordAuthenticatorProvider implements AuthenticationProvider {
    private final UserService userService;

    public EmailPasswordAuthenticatorProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.findByEmail(email);
        if (nonNull(user)) {
            if (password.equals(user.getSenha())) {
                return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }


}
