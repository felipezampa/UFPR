package br.ufpr.bantads.repository;

import br.ufpr.bantads.model.entity.Cliente;
import br.ufpr.bantads.model.entity.Gerente;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    public Cliente findByCpf(String cpf);

    @Query("SELECT u FROM Cliente u WHERE u.gerente.id = :gerenteId")
    List<Cliente> findByGerenteId(Long gerenteId);

    Cliente findFirstByGerenteId(Long gerenteId);

    void deleteByCpf(String cpf);

	public Cliente findBySaga(String id);

    List<Cliente> findByAprovado(Boolean aprovado);
}