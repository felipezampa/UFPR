package com.bantads.conta.REST;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.conta.DTOs.ContaDTO;
import com.bantads.conta.DTOs.ContaResponseDTO;
import com.bantads.conta.model.CUD.ContaCUD;
import com.bantads.conta.model.R.ContaR;
import com.bantads.conta.repository.CUD.ContaCUDRepository;
import com.bantads.conta.repository.R.ContaRRepository;
import com.bantads.conta.services.ProducerInsereConta;

@CrossOrigin
@RestController
public class ContaREST {
	@Autowired
	private ProducerInsereConta producerInsereConta;
	
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private ContaCUDRepository contaCUDRepository;
    
	@Autowired
	private ContaRRepository contaRRepository;
	
	@PostMapping("/contas")
	public ResponseEntity<ContaResponseDTO> insereConta(@RequestBody ContaDTO conta) {
		try {
            ContaCUD contaCUD = new ContaCUD(conta);
            contaCUDRepository.save(contaCUD);
            producerInsereConta.sendInserir(mapper.map(contaCUD, ContaResponseDTO.class));

			return ResponseEntity.ok().body(null);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/contas/{id}")
	public ResponseEntity<ContaResponseDTO> obterPorId(@PathVariable Long id) {
		try {
			Optional<ContaR> conta = contaRRepository.findByNumero(id);

			if (!conta.isPresent())
				return ResponseEntity.notFound().build();

			ContaResponseDTO response = mapper.map(conta, ContaResponseDTO.class);
			return ResponseEntity.ok().body(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/contas/cliente/{idCliente}")
	public ResponseEntity<ContaResponseDTO> obterPorIdCliente(@PathVariable String idCliente) {
		try {
			Optional<ContaR> conta = contaRRepository.findByIdCliente(idCliente);

			if (!conta.isPresent())
				return ResponseEntity.notFound().build();
			
			ContaResponseDTO response = mapper.map(conta, ContaResponseDTO.class);
			return ResponseEntity.ok().body(response);
		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
	
	@GetMapping("/contas/gerente/{idGerente}")
    public ResponseEntity<List<ContaResponseDTO>> obterPorIdGerente(@PathVariable String idGerente) {
        try {
        	Optional<List<ContaR>> contasOpt = contaRRepository.findByIdGerente(idGerente);
            
        	if (!contasOpt.isPresent())
                return ResponseEntity.notFound().build();

            List<ContaResponseDTO> contasResponseDTO = contasOpt.get().stream().map(e -> mapper.map(e, ContaResponseDTO.class)).collect(Collectors.toList());
            return ResponseEntity.ok(contasResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
