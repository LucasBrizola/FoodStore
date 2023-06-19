package com.foodstore.foodstore.service.exception.pedido;

public class PedidoMissingValueException extends RuntimeException{
    public PedidoMissingValueException() { super("pedido necessita de numero, cpf do cliente e lista de produtos obrigatoriamente"); }
}
