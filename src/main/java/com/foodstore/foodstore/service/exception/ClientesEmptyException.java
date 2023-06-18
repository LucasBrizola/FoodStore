package com.foodstore.foodstore.service.exception;

public class ClientesEmptyException extends RuntimeException{
    public ClientesEmptyException() { super("sem clientes cadastrados"); }
}
