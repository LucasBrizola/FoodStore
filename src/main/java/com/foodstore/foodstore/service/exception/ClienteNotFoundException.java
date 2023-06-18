package com.foodstore.foodstore.service.exception;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(String nome) { super("cliente " + nome + " não encontrado"); }
}
