package com.foodstore.foodstore.domain;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @Column(nullable = false)
    private Integer num;

    @ManyToOne
    @JoinColumn(name ="cliente_cpf", nullable = false)
    private Cliente cliente;
    @Column(nullable = false)
    private ArrayList<String> produtos;
    @Column(nullable = false)
    private double valorTotal;

    public Pedido(){}

    public Pedido(Integer num, Cliente cliente, ArrayList<String> produtos, double valorTotal){
        this.num = num;
        this.cliente = cliente;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<String> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(String produto){
        this.produtos.add(produto);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
