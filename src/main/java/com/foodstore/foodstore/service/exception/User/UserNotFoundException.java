package com.foodstore.foodstore.service.exception.User;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String email) { super("usuário " + email + " não encontrado"); }
}
