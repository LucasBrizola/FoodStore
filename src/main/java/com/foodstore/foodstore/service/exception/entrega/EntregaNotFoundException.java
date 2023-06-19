package com.foodstore.foodstore.service.exception.entrega;

public class EntregaNotFoundException extends RuntimeException{
    public EntregaNotFoundException(Long id) { super("entrega " + id + " não encontrada"); }
}
