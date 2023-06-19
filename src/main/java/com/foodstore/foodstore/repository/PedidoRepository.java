package com.foodstore.foodstore.repository;

import com.foodstore.foodstore.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByNum(Integer num);

    void deleteByNum(Integer num);
}
