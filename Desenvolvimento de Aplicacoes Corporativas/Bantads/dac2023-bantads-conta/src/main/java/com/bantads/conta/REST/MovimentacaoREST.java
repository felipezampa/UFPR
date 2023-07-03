package com.bantads.conta.REST;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.conta.DTOs.ContaResponseDTO;
import com.bantads.conta.DTOs.MovimentacaoDTO;
import com.bantads.conta.DTOs.MovimentacaoResponseDTO;
import com.bantads.conta.DTOs.MovimentacoesRequestDTO;
import com.bantads.conta.DTOs.MovimentacoesResponseDTO;
import com.bantads.conta.model.CUD.ContaCUD;
import com.bantads.conta.model.CUD.MovimentacaoCUD;
import com.bantads.conta.model.R.ContaR;
import com.bantads.conta.model.R.MovimentacaoR;
import com.bantads.conta.repository.CUD.ContaCUDRepository;
import com.bantads.conta.repository.CUD.MovimentacaoCUDRepository;
import com.bantads.conta.repository.R.ContaRRepository;
import com.bantads.conta.repository.R.MovimentacaoRRepository;
import com.bantads.conta.services.ProducerAtualizaConta;
import com.bantads.conta.services.ProducerInsereMovimentacao;

@CrossOrigin
@RestController
public class MovimentacaoREST {
	@Autowired
	private ContaRRepository contaRRepository;
	
	@Autowired
	private ContaCUDRepository contaCUDRepository;

	@Autowired
	private MovimentacaoCUDRepository movimentacaoCUDRepository;

	@Autowired
	private MovimentacaoRRepository movimentacaoRRepository;

	@Autowired
	private ProducerInsereMovimentacao producerInsereMovimentacao;

	@Autowired
	private ProducerAtualizaConta producerAtualizaConta;

	@Autowired
	private ModelMapper mapper;

	@PostMapping("/movimentacoes")
	@Transactional
	public ResponseEntity<?> insere(@RequestBody MovimentacaoDTO movimentacao) {

		try {
			MovimentacaoCUD movimentacaoC = new MovimentacaoCUD(movimentacao);

			Optional<ContaCUD> origemOpt = contaCUDRepository.findById(movimentacaoC.getContaOrigem());
			if (!origemOpt.isPresent())
				return ResponseEntity.notFound().build();

			ContaCUD contaOrigem = origemOpt.get();
			ContaCUD contaDestino = null;

			if (movimentacaoC.getContaDestino() != null) {
				Optional<ContaCUD> destinoOpt = contaCUDRepository.findById(movimentacaoC.getContaDestino());

				if (!destinoOpt.isPresent())
					return ResponseEntity.notFound().build();
				contaDestino = destinoOpt.get();
			}

			// ATUALIZA O SALDO DA CONTA
			switch (movimentacaoC.getTipoMovimentacao()) {

			case 1:// deposito
				contaOrigem.setSaldo(contaOrigem.getSaldo().add(movimentacaoC.getValor()));
				contaCUDRepository.save(contaOrigem);
				producerAtualizaConta.sendAtualiza(mapper.map(contaOrigem, ContaResponseDTO.class));
				movimentacaoCUDRepository.save(movimentacaoC);
				break;

			case 2:// saque
				if (movimentacaoC.getValor().compareTo(contaOrigem.getSaldo().add(contaOrigem.getLimite())) == 1) {
					return ResponseEntity.status(400).body("Saque maior que o saldo disponível");
				} else {
					contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(movimentacaoC.getValor()));
					contaCUDRepository.save(contaOrigem);
					producerAtualizaConta.sendAtualiza(mapper.map(contaOrigem, ContaResponseDTO.class));
					movimentacaoCUDRepository.save(movimentacaoC);
				}
				break;

			case 3:// transferencia
				if (movimentacaoC.getValor().compareTo(contaOrigem.getSaldo().add(contaOrigem.getLimite())) == 1) {
					return ResponseEntity.status(400).build();
				} else if (contaDestino == null) {
					return ResponseEntity.notFound().build();
				} else {
					BigDecimal saldoContaOrigem = contaOrigem.getSaldo().subtract(movimentacaoC.getValor());
					BigDecimal saldoContaDestino = contaDestino.getSaldo().add(movimentacaoC.getValor());

					contaOrigem.setSaldo(saldoContaOrigem);
					contaCUDRepository.saveAndFlush(contaOrigem);

					contaDestino.setSaldo(saldoContaDestino);
					contaCUDRepository.saveAndFlush(contaDestino);

					producerAtualizaConta.sendAtualiza(mapper.map(contaOrigem, ContaResponseDTO.class));
					producerAtualizaConta.sendAtualiza(mapper.map(contaDestino, ContaResponseDTO.class));
					movimentacaoCUDRepository.save(movimentacaoC);
				}
				break;

			default:
				return ResponseEntity.status(400).build();
			}

			producerInsereMovimentacao.sendInserir(mapper.map(movimentacaoC, MovimentacaoResponseDTO.class));

			return ResponseEntity.ok().body(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping("/movimentacoes/{conta}")
	public ResponseEntity<MovimentacoesResponseDTO> obtemPorConta(@PathVariable Long conta) {
		try {
			List<MovimentacaoR> movimentacoes = movimentacaoRRepository.obtemMovimentacoesConta(conta);
			MovimentacoesResponseDTO response = new MovimentacoesResponseDTO(movimentacoes);
			return ResponseEntity.ok().body(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}

	@PostMapping("/movimentacoes/data")
	public ResponseEntity<MovimentacoesResponseDTO> obtemPorData(@RequestBody MovimentacoesRequestDTO requestDTO) {
		
		Optional<ContaR> conta = contaRRepository.findByIdCliente(requestDTO.getIdCliente());

		if (!conta.isPresent())
			return ResponseEntity.notFound().build();
		
		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate startDate = LocalDate.parse(requestDTO.getDataInicio(), formatter);
			LocalDate endDate = LocalDate.parse(requestDTO.getDataFim(), formatter).plusDays(1);
			System.out.println(startDate);

			List<MovimentacaoR> movimentacoes = movimentacaoRRepository.obtemMovimentacoesContaData(
					conta.get().getNumero(), java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
			MovimentacoesResponseDTO response = new MovimentacoesResponseDTO(movimentacoes);
			return ResponseEntity.ok().body(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
}
