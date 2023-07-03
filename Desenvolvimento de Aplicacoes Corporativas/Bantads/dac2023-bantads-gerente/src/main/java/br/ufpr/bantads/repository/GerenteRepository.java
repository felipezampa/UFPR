package br.ufpr.bantads.repository;

import org.springframework.data.jpa.repository.*;
import br.ufpr.bantads.model.entity.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long>{
	
	public Gerente findByCpf(String cpf);
	public Gerente findFirstByOrderByNumClientes();
	public Gerente findFirstByOrderByNumClientesDesc();
}
