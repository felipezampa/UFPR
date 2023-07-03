package com.bantads.cliente.bantadscliente.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bantads.cliente.bantadscliente.model.Cliente;
import com.bantads.cliente.bantadscliente.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, String> {
    public Optional<Endereco> findById(String id);
}
