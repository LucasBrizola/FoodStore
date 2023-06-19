package com.foodstore.foodstore.service.exception.cliente;

public class ClientesEmptyException extends RuntimeException{
    public ClientesEmptyException() { super("sem clientes cadastrados"); }
}
