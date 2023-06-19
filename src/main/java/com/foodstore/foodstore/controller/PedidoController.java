package com.foodstore.foodstore.controller;


import com.foodstore.foodstore.domain.Pedido;
import com.foodstore.foodstore.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Transactional
@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido createPedido(@RequestBody @Valid Pedido pedido){
        return pedidoService.save(pedido);
    }

    @PostMapping(value="/num/{num}")
    @ResponseStatus(HttpStatus.OK)
    public Pedido addProduto(@PathVariable("num") Integer num, @RequestBody @Valid String produto){
        return pedidoService.addProduto(num, produto);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoService.findAll();
        return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
    }

    @GetMapping(value="/num/{num}")
    public ResponseEntity<?> findByCpf(@PathVariable("num") Integer num) {
        Optional<Pedido> pedido = Optional.ofNullable(this.pedidoService.findByNum(num));
        return new ResponseEntity<Optional<Pedido>>(pedido,HttpStatus.OK);
    }

    @PutMapping(value="/num/{num}")
    public ResponseEntity<?> editar(@PathVariable("num") Integer num, @RequestBody Pedido pedido) {
        Optional<Pedido> pedidoNew = Optional.ofNullable(this.pedidoService.update(num, pedido));
        return new ResponseEntity<Optional<Pedido>>(pedidoNew, HttpStatus.OK);
    }


    @DeleteMapping(value="/num/{num}")
    public ResponseEntity<?> deleteByNum(@PathVariable("num") Integer num) {
        this.pedidoService.delete(num);
        return new ResponseEntity<>("pedido de numero " + num + " excluido", HttpStatus.OK);
    }
}
