package com.foodstore.foodstore.controller;

import com.foodstore.foodstore.domain.Entrega;
import com.foodstore.foodstore.service.EntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrega")
public class EntregaController {
    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService){
        this.entregaService = entregaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega createEntrega(@RequestBody @Valid Entrega entrega){
        return entregaService.save(entrega);
    }

    @PostMapping(value="/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Entrega Entregue(@PathVariable("id") Long id){
        entregaService.entregue(id);
        return entregaService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> findAll() {
        List<Entrega> entregas = entregaService.findAll();
        return new ResponseEntity<List<Entrega>>(entregas, HttpStatus.OK) ;
    }

    @GetMapping(value="/id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Entrega> entrega = Optional.ofNullable(this.entregaService.findById(id));
        return new ResponseEntity<Optional<Entrega>>(entrega,HttpStatus.OK);
    }

    @PutMapping(value="/id/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id, @RequestBody Entrega entrega) {
        Optional<Entrega> entregaNew = Optional.ofNullable(this.entregaService.update(id, entrega));
        return new ResponseEntity<Optional<Entrega>>(entregaNew, HttpStatus.OK);
    }

    @DeleteMapping(value="/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.entregaService.delete(id);
        return new ResponseEntity<String>("entrega de id " + id + " excluido", HttpStatus.OK);
    }
}
