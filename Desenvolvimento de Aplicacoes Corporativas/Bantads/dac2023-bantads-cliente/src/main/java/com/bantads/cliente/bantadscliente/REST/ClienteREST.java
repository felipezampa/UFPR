package com.bantads.cliente.bantadscliente.REST;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bantads.cliente.bantadscliente.DTOs.RabbitContaDTO;
import com.bantads.cliente.bantadscliente.DTOs.RabbitLoginDTO;
import com.bantads.cliente.bantadscliente.DTOs.PostClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.PutClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.ResponseClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.ResponseClienteSagaDTO;
import com.bantads.cliente.bantadscliente.mapper.ClienteMapper;
import com.bantads.cliente.bantadscliente.model.Cliente;
import com.bantads.cliente.bantadscliente.repository.ClienteRepository;
import com.bantads.cliente.bantadscliente.services.Producer.Usuario.ProducerAlteraConta;
import com.bantads.cliente.bantadscliente.services.Producer.Usuario.ProducerAlteraLogin;
import com.bantads.cliente.bantadscliente.services.Producer.Usuario.ProducerNovaConta;
import com.bantads.cliente.bantadscliente.services.Producer.Usuario.ProducerNovoLogin;
import com.bantads.cliente.bantadscliente.validator.ClienteValidator;

@CrossOrigin
@RestController
@RequestMapping("clientes")
public class ClienteREST {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProducerNovoLogin producerNovoLogin;

	@Autowired
	private ProducerNovaConta producerNovaConta;

	@Autowired
	private ProducerAlteraLogin producerAlteraLogin;

	@Autowired
	private ProducerAlteraConta producerAlteraConta;

	@PostMapping("/self-registration")
	public ResponseEntity<String> update(@RequestBody PostClienteDTO postClienteDTO) {
		try {
			if (ClienteValidator.validate(postClienteDTO))
				return ResponseEntity.badRequest().body("Falha na validação. Verifique os dados");

			Optional<Cliente> clienteOp = clienteRepository.findFirstByCpf(postClienteDTO.getCpf());

			if (clienteOp.isPresent())
				return ResponseEntity.badRequest().body("CPF já cadastrado");

			Cliente cliente = new Cliente();
			ClienteMapper.map(cliente, postClienteDTO, mapper);

			cliente.getEndereco().setId(UUID.randomUUID().toString());
			cliente.setId(UUID.randomUUID().toString());
			RabbitLoginDTO novoLoginDTO = new RabbitLoginDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getEmail(), cliente.getSenha());

			RabbitContaDTO novaContaDTO = new RabbitContaDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getEmail(), cliente.getSalario());

			clienteRepository.save(cliente);
			producerNovoLogin.send(novoLoginDTO);
			producerNovaConta.send(novaContaDTO);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());

		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable("id") String id, @RequestBody PutClienteDTO putClienteDTO) {
		try {
			if (ClienteValidator.validate(putClienteDTO))
				return ResponseEntity.badRequest().build();

			Optional<Cliente> clienteOp = clienteRepository.findById(id);
			if (!clienteOp.isPresent())
				return ResponseEntity.notFound().build();

			Cliente cliente = clienteOp.get();
			cliente.setId(id);
			ClienteMapper.map(cliente, putClienteDTO, mapper);

			RabbitContaDTO alteraContaDTO = new RabbitContaDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getEmail(), cliente.getSalario());

			RabbitLoginDTO alteraLoginDTO = new RabbitLoginDTO(cliente.getId(), cliente.getNome(), cliente.getCpf(),
					cliente.getEmail(), cliente.getSenha());

			clienteRepository.save(cliente);
			producerAlteraConta.send(alteraContaDTO);
			producerAlteraLogin.send(alteraLoginDTO);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseClienteDTO> getCliente(@PathVariable String id) {
		try {
			Optional<Cliente> clienteOp = clienteRepository.findById(id);

			if (!clienteOp.isPresent())
				return ResponseEntity.notFound().build();

			Cliente cliente = clienteOp.get();
			ResponseClienteDTO ResponseClienteDTO = mapper.map(cliente, ResponseClienteDTO.class);
			return ResponseEntity.ok(ResponseClienteDTO);
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping("/saga/{id}")
	public ResponseEntity<ResponseClienteSagaDTO> getClienteSaga(@PathVariable String id) {
		try {
			Optional<Cliente> clienteOp = clienteRepository.findById(id);

			if (!clienteOp.isPresent())
				return ResponseEntity.notFound().build();

			Cliente cliente = clienteOp.get();
			System.out.println(id);

			System.out.println(cliente.getId());
			ResponseClienteSagaDTO responseClienteDTO = mapper.map(cliente, ResponseClienteSagaDTO.class);
			responseClienteDTO.setEmail(cliente.getEmail());
			return ResponseEntity.ok(responseClienteDTO);
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<ResponseClienteDTO>> getClientes() {
		try {
			List<Cliente> clientes = clienteRepository.findAll();
			List<ResponseClienteDTO> responseClienteDTOs = clientes.stream()
					.map(cliente -> mapper.map(cliente, ResponseClienteDTO.class)).collect(Collectors.toList());
			return ResponseEntity.ok(responseClienteDTOs);
		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	@GetMapping("/by-cpf/{cpf}")
	public ResponseEntity<ResponseClienteDTO> getClienteByCPF(@PathVariable String cpf) {
		try {
			if (ClienteValidator.validateCpf(cpf))
				return ResponseEntity.badRequest().build();

			Optional<Cliente> clienteOp = clienteRepository.findFirstByCpf(cpf);
			if (!clienteOp.isPresent())
				return ResponseEntity.notFound().build();

			Cliente cliente = clienteOp.get();
			ResponseClienteDTO ResponseClienteDTO = mapper.map(cliente, ResponseClienteDTO.class);
			return ResponseEntity.ok(ResponseClienteDTO);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(500).build();
		}
	}
}
