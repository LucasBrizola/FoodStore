package com.foodstore.foodstore.service.exception.cliente;

public class ClienteNotFoundException extends RuntimeException{
    public ClienteNotFoundException(String cpf) { super("cliente " + cpf + " não encontrado"); }
}
