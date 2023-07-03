package com.bantads.conta.repository.CUD;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import com.bantads.conta.model.CUD.MovimentacaoCUD;

public interface MovimentacaoCUDRepository extends JpaRepository<MovimentacaoCUD, Long> {
	public Optional<MovimentacaoCUD> findById(Long id);
}
