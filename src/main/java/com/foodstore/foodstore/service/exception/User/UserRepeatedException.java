package com.foodstore.foodstore.service.exception.User;

public class UserRepeatedException extends RuntimeException{
    public UserRepeatedException(String email) { super("usuário de email " + email + " já existe!"); }
}
