package com.foodstore.foodstore.service.exception.entrega;

public class EntregaRepeatedException extends RuntimeException{
    public EntregaRepeatedException(Long id) { super("entrega de id " + id + " já existe!"); }
}
