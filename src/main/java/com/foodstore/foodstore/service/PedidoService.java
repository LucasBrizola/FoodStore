package com.foodstore.foodstore.service;

import com.foodstore.foodstore.domain.Pedido;
import com.foodstore.foodstore.repository.PedidoRepository;
import com.foodstore.foodstore.service.exception.pedido.PedidoMissingValueException;
import com.foodstore.foodstore.service.exception.pedido.PedidoNotFoundException;
import com.foodstore.foodstore.service.exception.pedido.PedidoRepeatedException;
import com.foodstore.foodstore.service.exception.pedido.PedidosEmptyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteService clienteService;

    public PedidoService(PedidoRepository pedidoRepository, ClienteService clienteService) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService = clienteService;
    }

    public Pedido save(Pedido pedido) {
        if (pedidoRepository.findByNum(pedido.getNum()).isPresent()) {
            throw new PedidoRepeatedException(pedido.getNum());
        }
        if (verifyFields(pedido)) {
            return this.pedidoRepository.save(pedido);
        }
        return null;
    }

    private boolean verifyFields(Pedido pedido) {
        if (pedido.getCliente().getCpf() == null || pedido.getProdutos() == null || pedido.getValorTotal() == 0.0 ) {
            throw new PedidoMissingValueException();
        }
        clienteService.findByCpf(pedido.getCliente().getCpf());
        return true;
    }


    public Pedido addProduto(Integer num, String produto) {
        Pedido pedido = findByNum(num);
        pedido.getProdutos().add(produto);
        return pedido;
    }

    public Pedido findByNum(Integer num) {
        return pedidoRepository.findByNum(num)
                .orElseThrow(() -> new PedidoNotFoundException(num));
    }

    public List<Pedido> findAll() {
        List<Pedido> lista = this.pedidoRepository.findAll();
        if (lista.isEmpty()) {
            throw new PedidosEmptyException();
        } else return lista;
    }

    public Pedido update(Integer num, Pedido pedidoNew) {
        Pedido pedidoOld = this.findByNum(num);
        replaceFields(pedidoNew, pedidoOld);
        this.pedidoRepository.save(pedidoNew);
        return pedidoNew;
    }

    private void replaceFields(Pedido pedidoNew, Pedido pedidoOld) {
        pedidoNew.setNum(pedidoOld.getNum());

        if (pedidoNew.getCliente() == null) {
            pedidoNew.setCliente(pedidoOld.getCliente());
        }

        if (pedidoNew.getProdutos() == null) {
            pedidoNew.setProdutos(pedidoOld.getProdutos());
        }

        if (pedidoNew.getValorTotal() == 0.0) {
            pedidoNew.setValorTotal(pedidoOld.getValorTotal());
        }
    }
    public void delete(Integer num) {
        if (pedidoRepository.findByNum(num).isPresent()) {
            this.pedidoRepository.deleteByNum(num);
        } else throw new PedidoNotFoundException(num);
    }
}
