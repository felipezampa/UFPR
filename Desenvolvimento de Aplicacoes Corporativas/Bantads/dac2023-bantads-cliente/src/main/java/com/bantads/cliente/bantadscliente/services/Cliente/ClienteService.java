package com.bantads.cliente.bantadscliente.services.Cliente;

import java.math.BigDecimal;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bantads.cliente.bantadscliente.DTOs.PutClienteDTO;
import com.bantads.cliente.bantadscliente.DTOs.RabbitContaDTO;
import com.bantads.cliente.bantadscliente.DTOs.RabbitLoginDTO;
import com.bantads.cliente.bantadscliente.mapper.ClienteMapper;
import com.bantads.cliente.bantadscliente.model.Cliente;
import com.bantads.cliente.bantadscliente.model.Endereco;
import com.bantads.cliente.bantadscliente.model.Estado;
import com.bantads.cliente.bantadscliente.repository.ClienteRepository;
import com.bantads.cliente.bantadscliente.repository.EnderecoRepository;
import com.bantads.cliente.bantadscliente.services.rabbitMQ.RabbitMqController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private RabbitMqController rabbitMqController;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper objectMapper;

	public void RegistrarCliente(JsonNode node) throws JsonProcessingException {
		System.out.println("ClienteService -> RegistrarCliente");

		try {
			JsonNode clienteNode = node.get("manager");
			System.out.println(clienteNode.toString());
			JsonNode enderecoNode = node.get("manager").get("endereco");
			Estado estado = Estado.valueOf(enderecoNode.get("estado").asText());

			Endereco endereco = new Endereco(UUID.randomUUID().toString(), enderecoNode.get("cep").asText(), enderecoNode.get("logradouro").asText(),
					enderecoNode.get("numero").asInt(), enderecoNode.get("complemento").asText(),
					enderecoNode.get("bairro").asText(), enderecoNode.get("cidade").asText(), estado);

			Cliente cliente = new Cliente(
					node.get("manager").get("id").asText(),
					clienteNode.get("nome").asText(), clienteNode.get("cpf").asText(), endereco,
					new BigDecimal(clienteNode.get("salario").asText()), clienteNode.get("senha").asText(),
					clienteNode.get("email").asText(), UUID.fromString(clienteNode.get("saga").asText()), clienteNode.get("telefone").asText());


			clienteRepository.save(cliente);

			rabbitMqController.enviarMensagem(node, "customer-ok", "OK");
		} catch (Exception e) {
			rabbitMqController.enviarMensagem(node, "customer-error", "ERROR");
			e.printStackTrace();
		}
	}

	public void AtualizarCliente(JsonNode node) {
		System.out.println("ClienteService -> AtualizarCliente");

		try {

			JsonNode clienteNode = node.get("manager");

			Cliente clienteUpdated = objectMapper.treeToValue(clienteNode, Cliente.class);

			Cliente cliente = clienteRepository.findById(clienteNode.get("id").asText()).orElse(null);
			cliente.setId(clienteUpdated.getId());
			ClienteMapper.map(cliente, clienteUpdated, mapper);

			clienteRepository.save(cliente);

			rabbitMqController.enviarMensagem(node, "customer-update-ok", "OK");
		} catch (Exception e) {
			rabbitMqController.enviarMensagem(node, "customer-update-error", "ERROR");
			e.printStackTrace();
		}
	}

	public void DeletarCliente(JsonNode node) {
		System.out.println("ClienteService -> DeletarCliente");

		try {
			JsonNode clienteNode = node.get("manager");

			Cliente cliente = clienteRepository.findById(UUID.fromString(clienteNode.get("id").asText()).toString())
					.orElse(null);

			clienteRepository.deleteById(cliente.getId());

			rabbitMqController.enviarMensagem(node, "customer-delete-ok", "OK");
		} catch (Exception e) {
			rabbitMqController.enviarMensagem(node, "customer-delete-error", "ERROR");
			e.printStackTrace();
		}
	}
}
