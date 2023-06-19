package com.foodstore.foodstore.service.exception.entrega;

public class EntregasEmptyException extends RuntimeException{
    public EntregasEmptyException() { super("sem entregas cadastradas"); }
}
