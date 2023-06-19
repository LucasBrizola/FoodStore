package com.foodstore.foodstore.service;

import com.foodstore.foodstore.domain.Entrega;
import com.foodstore.foodstore.repository.EntregaRepository;
import com.foodstore.foodstore.service.exception.entrega.EntregaMissingValueException;
import com.foodstore.foodstore.service.exception.entrega.EntregaNotFoundException;
import com.foodstore.foodstore.service.exception.entrega.EntregaRepeatedException;
import com.foodstore.foodstore.service.exception.entrega.EntregasEmptyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;


    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega save(Entrega entrega) {
        if (entregaRepository.findById(entrega.getId()).isPresent()) {
            throw new EntregaRepeatedException(entrega.getId());
        }
        if (verifyFields(entrega)) {
            return this.entregaRepository.save(entrega);
        }
        return null;
    }

    private boolean verifyFields(Entrega entrega) {
        if (entrega.getId() == null || entrega.getPedido() == null) {
            throw new EntregaMissingValueException();
        }
        return true;
    }

    public void entregue(Long id){
        Entrega entrega = findById(id);
        entrega.setEntregue();
    }

    public Entrega findById(Long id) {
        return entregaRepository.findById(id)
                .orElseThrow(() -> new EntregaNotFoundException(id));
    }

    public List<Entrega> findAll() {
        List<Entrega> lista = this.entregaRepository.findAll();
        if (lista.isEmpty()) {
            throw (new EntregasEmptyException());
        } else return lista;
    }

    public Entrega update(Long id, Entrega entregaNew) {
        Entrega entregaOld = this.findById(id);
        replaceFields(entregaNew, entregaOld);
        this.entregaRepository.save(entregaNew);
        return entregaNew;
    }

    private void replaceFields(Entrega entregaNew, Entrega entregaOld) {
        entregaNew.setId(entregaOld.getId());

        if (entregaNew.getPedido() == null) {
            entregaNew.setPedido(entregaOld.getPedido());
        }
    }

    public void delete(Long id) {
        if (entregaRepository.findById(id).isPresent()) {
            this.entregaRepository.deleteById(id);
        } else throw new EntregaNotFoundException(id);
    }


}
