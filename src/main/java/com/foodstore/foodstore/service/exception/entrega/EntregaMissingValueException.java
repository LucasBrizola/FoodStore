package com.foodstore.foodstore.service.exception.entrega;

public class EntregaMissingValueException extends RuntimeException{
    public EntregaMissingValueException() { super("entrega necessita de id e pedido obrigatoriamente"); }
}
