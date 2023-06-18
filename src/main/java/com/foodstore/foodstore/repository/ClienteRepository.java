package com.foodstore.foodstore.repository;

import com.foodstore.foodstore.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByCpf(String cpf);
    void deleteByCpf(String cpf);
}
