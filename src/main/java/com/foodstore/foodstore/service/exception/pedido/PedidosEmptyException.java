package com.foodstore.foodstore.service.exception.pedido;

public class PedidosEmptyException extends RuntimeException{
    public PedidosEmptyException() { super("sem pedidos cadastrados"); }
}
