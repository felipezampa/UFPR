package com.bantads.conta.repository.CUD;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bantads.conta.model.CUD.ContaCUD;

import jakarta.transaction.Transactional;

@Repository
public interface ContaCUDRepository extends JpaRepository<ContaCUD, Long> {
	public Optional<ContaCUD> findByNumero(Long numero);
	public Optional<ContaCUD> findByIdCliente(String idCliente);
}
