package com.bantads.orchestration.bantadsorchestration.services.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SelfregistrationResponseAuth {
	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = "orchestration-response-auth")
	public void receiveRead(@Payload String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			String action = node.get("action").asText();
			String id = node.get("manager").get("id").asText();
			if (action.equals("auth-ok")) {
				System.out.println("orchestration-response-auth(" + id + "): OK");
			} else if (action.equals("auth-updated")) {
				System.out.println("orchestration-response-auth(" + id + "): ATUALIZADO	OK");
			} else if (action.equals("auth-deleted")) {
				System.out.println("orchestration-response-auth(" + id + "): ATUALIZADO	OK");
			}else if (action.equals("auth-duplicated")) {
				System.out.println("orchestration-response-auth(" + id + "): DUPLICADO");
			} else if (action.equals("auth-delete-error")) {
				System.out.println("orchestration-response-auth(" + id + "): ERRO AO DELETAR CLIENTE");
			} else if (action.equals("auth-error")) {
				// CASO DE ROLLBACK
				System.out.println("orchestration-response-auth(" + id + "): ERRO GENÉRICO");
			} else {
				System.out.println("orchestration-response-auth(" + id + "): ACTION DESCONHECIDA - " + action);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
