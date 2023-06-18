package com.foodstore.foodstore.controller;

import com.foodstore.foodstore.domain.Cliente;
import com.foodstore.foodstore.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente createCliente(@RequestBody @Valid Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = clienteService.findAll();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK) ;
    }

    @GetMapping(value="/cpf/{cpf}")
    public ResponseEntity<?> findByCpf(@PathVariable("cpf") String cpf) {
        Optional<Cliente> cliente = Optional.ofNullable(this.clienteService.findByCpf(cpf));
        return new ResponseEntity<Optional<Cliente>>(cliente,HttpStatus.OK);
    }

    @PutMapping(value="/cpf/{cpf}")
    public ResponseEntity<?> editar(@PathVariable("cpf") String cpf, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteNew = Optional.ofNullable(this.clienteService.update(cpf, cliente));
        return new ResponseEntity<Optional<Cliente>>(clienteNew, HttpStatus.OK);
    }

    @DeleteMapping(value="/cpf/{cpf}")
    public ResponseEntity<?> deleteByCpf(@PathVariable("cpf") String cpf) {
        this.clienteService.delete(cpf);
        return new ResponseEntity<String>("cliente de cpf " + cpf + " excluido", HttpStatus.OK);
    }
}
