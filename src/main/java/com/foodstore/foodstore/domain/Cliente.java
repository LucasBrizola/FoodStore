package com.foodstore.foodstore.domain;


import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    private String endereco;
    private String telefone;

    public Cliente() {
    }
    public Cliente(String cpf, String nome, String email, String endereco, String telefone) {
    this.cpf = cpf;
    this.nome = nome;
    this.email = email;
    this.endereco = endereco;
    this.telefone = telefone;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }
}