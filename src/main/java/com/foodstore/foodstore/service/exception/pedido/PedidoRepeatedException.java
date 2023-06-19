package com.foodstore.foodstore.service.exception.pedido;

public class PedidoRepeatedException extends RuntimeException{
    public PedidoRepeatedException(Integer num) { super("pedido de numero " + num + " já existe!"); }
}
