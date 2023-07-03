package com.bantads.conta.repository.R;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bantads.conta.model.R.MovimentacaoR;

public interface MovimentacaoRRepository extends JpaRepository<MovimentacaoR, Long> {
	public Optional<MovimentacaoR> findById(Long id);
	@Query(value = "select m.id, m.valor, m.data_hora_criacao, m.tipo_movimentacao, m.numero_conta_origem, m.numero_conta_destino from tb_movimentacao m where (numero_conta_origem = :idConta or numero_conta_destino = :idConta)", nativeQuery = true)
	public List<MovimentacaoR> obtemMovimentacoesConta(@Param("idConta") Long idConta);
	@Query(value = "select m.id, m.valor, m.data_hora_criacao, m.tipo_movimentacao, m.numero_conta_origem, m.numero_conta_destino from tb_movimentacao m where (numero_conta_origem = :idConta or numero_conta_destino = :idConta) and m.data_hora_criacao >= :dataInicio and m.data_hora_criacao <= :dataFim ", nativeQuery = true)
	public List<MovimentacaoR> obtemMovimentacoesContaData(@Param("idConta") Long idConta, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);
}
