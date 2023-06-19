package com.foodstore.foodstore.service.exception.pedido;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(Integer num) { super("pedido " + num + " não encontrado"); }
}
