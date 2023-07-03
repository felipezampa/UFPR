package com.bantads.cliente.bantadscliente.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bantads.cliente.bantadscliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    public Optional<Cliente> findFirstByCpf(String cpf);
    @Transactional
    public Long deleteBySaga(UUID saga);
	public Optional<Cliente> findById(String id);
}
