package com.foodstore.foodstore.service.exception;

public class ClienteMissingValueException extends RuntimeException{
    public ClienteMissingValueException() { super("cliente necessita de nome, cpf e email obrigatoriamente"); }
}
