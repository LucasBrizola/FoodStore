package com.foodstore.foodstore.service.exception.cliente;

public class ClienteMissingValueException extends RuntimeException{
    public ClienteMissingValueException() { super("cliente necessita de nome, cpf e email obrigatoriamente"); }
}
