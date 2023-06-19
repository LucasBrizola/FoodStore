package com.foodstore.foodstore.service.exception.User;

public class ClientesEmptyException extends RuntimeException{
    public ClientesEmptyException() { super("sem clientes cadastrados"); }
}
