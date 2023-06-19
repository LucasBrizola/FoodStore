package com.foodstore.foodstore.service.exception.User;

public class UserMissingValueException extends RuntimeException{
    public UserMissingValueException() { super("usuário necessita de email e senha obrigatoriamente"); }
}
