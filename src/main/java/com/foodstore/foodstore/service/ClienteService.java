package com.foodstore.foodstore.service;

import com.foodstore.foodstore.domain.Cliente;
import com.foodstore.foodstore.repository.ClienteRepository;
import com.foodstore.foodstore.service.exception.ClienteMissingValueException;
import com.foodstore.foodstore.service.exception.ClienteNotFoundException;
import com.foodstore.foodstore.service.exception.ClienteRepeatedException;
import com.foodstore.foodstore.service.exception.ClientesEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;


    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente save(Cliente cliente) {
        if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
            throw new ClienteRepeatedException(cliente.getCpf());
        }
        if (verifyFields(cliente)) {
            return this.clienteRepository.save(cliente);
        }
        return null;
    }

    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNotFoundException(cpf));
    }

    public List<Cliente> list() {
        List<Cliente> lista = this.clienteRepository.findAll();
        if (lista.isEmpty()) {
            throw (new ClientesEmptyException());
        } else return lista;
    }

    public Cliente update(String cpf, Cliente clienteNew) {
        Cliente clienteOld = this.findByCpf(cpf);
        replaceFields(clienteNew, clienteOld);
        this.clienteRepository.save(clienteNew);
        return clienteNew;
    }

    public void delete(String cpf) {
        if (clienteRepository.findByCpf(cpf).isPresent()) {
            this.clienteRepository.deleteById(cpf);
        } else throw new ClienteNotFoundException(cpf);
    }


    private boolean verifyFields(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getCpf() == null || cliente.getEmail() == null) {
            throw new ClienteMissingValueException();
        }
        return true;
    }

    private Cliente replaceFields(Cliente clienteNew, Cliente clienteOld) {
            clienteNew.setCpf(clienteOld.getCpf());

        if (clienteNew.getNome() == null) {
            clienteNew.setNome(clienteOld.getNome());
        }

        if (clienteNew.getEmail() == null) {
            clienteNew.setEmail(clienteOld.getEmail());
        }

        if (clienteNew.getEndereco() == null) {
            clienteNew.setEndereco(clienteOld.getEndereco());
        }

        if (clienteNew.getTelefone() == null) {
            clienteNew.setTelefone(clienteOld.getTelefone());
        }


        return clienteNew;
    }

}
