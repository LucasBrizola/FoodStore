package com.foodstore.foodstore.domain;

import java.io.Serializable;
//devido a problemas deserialização, Produto foi descontinuado
public class Produto implements Serializable {

    private static final long serialVersionUID = 3254400162030203560L;

    private String nome;
    private Integer quantidade;
    private double preco;
    private double precoTotal;

    public Produto(){}

    public Produto(String nome, Integer quantidade, double preco){
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public void calculatePrecoTotal() {
        this.precoTotal = quantidade * preco;
    }

    public double getPrecoTotal(){
        return precoTotal;
    }
}
