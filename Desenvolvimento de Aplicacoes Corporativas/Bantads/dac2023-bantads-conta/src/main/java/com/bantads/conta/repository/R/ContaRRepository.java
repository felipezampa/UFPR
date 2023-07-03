package com.bantads.conta.repository.R;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bantads.conta.model.R.ContaR;

@Repository
public interface ContaRRepository extends JpaRepository<ContaR, Long> {
	public Optional<ContaR> findByNumero(Long numero);
	public Optional<ContaR> findByIdCliente(String idCliente);
	public Optional<List<ContaR>> findByIdGerente(String idExternoGerente);
}
