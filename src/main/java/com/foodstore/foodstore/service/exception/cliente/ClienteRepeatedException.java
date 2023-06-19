package com.foodstore.foodstore.service.exception.cliente;

public class ClienteRepeatedException extends RuntimeException{
    public ClienteRepeatedException(String cpf) { super("cliente de cpf " + cpf + " já existe!"); }
}
