package com.bantads.auth.consumer;

import java.util.Base64;
import java.util.UUID;

import com.bantads.auth.services.Usuario.UsuarioService;
import com.bantads.auth.services.rabbitMQ.RabbitMqController;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.auth.model.Usuario;
import com.bantads.auth.repository.UsuarioRepository;
import com.bantads.auth.utils.PasswordEncoderUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerUsuario {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RabbitMqController rabbitMqController;
	@Autowired
	private UsuarioService usuarioService;

	@RabbitListener(queues = "orchestration-selfregistration-auth")
	public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
		try {
			System.out.println(json);
			// Converte o payload em um JsonNode (conteúdo do payload)
			ObjectMapper mapper = new ObjectMapper();
			// Extrai a ação do payload
			JsonNode node = mapper.readTree(json);
			String action = node.get("action").asText();

			// ação recebida = auth-register -> extrai os campos
			if (action.equals("auth-register")) {
				usuarioService.RegistroUsuario(node);
			} else if (action.equals("auth-update")) {
				usuarioService.UpdateUsuario(node);
			} else if (action.equals("auth-delete")) {
				usuarioService.DeleteUsuario(node);
			} else if (action.equals("manager-customer-approve")) {
				usuarioService.aprovarLogin(node);
			} else if (action.equals("manager-auth-create")) {
				usuarioService.RegistroGerente(node);
			} else if (action.equals("manager-auth-update")) {
				usuarioService.UpdateGerente(node);
			} else if (action.equals("manager-auth-delete")) {
				usuarioService.DeleteGerente(node);
			} else {
				rabbitMqController.enviarMensagem(node, "auth-error", action);
			}

		} catch (Exception e) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			rabbitMqController.enviarMensagem(node, "auth-error", "ERROR");
			System.out.println(e);
		}
	}
}
