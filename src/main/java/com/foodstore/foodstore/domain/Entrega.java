package com.foodstore.foodstore.domain;

import javax.persistence.*;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_num", referencedColumnName = "num")
    private Pedido pedido;

    private boolean entregue = false;

    public Entrega(){};

    public Entrega(Long id, Pedido pedido){
        this.id = id;
        this.pedido = pedido;
    };

    public void setEntregue(){
        this.entregue = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean getEntregue(){
        return entregue;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
