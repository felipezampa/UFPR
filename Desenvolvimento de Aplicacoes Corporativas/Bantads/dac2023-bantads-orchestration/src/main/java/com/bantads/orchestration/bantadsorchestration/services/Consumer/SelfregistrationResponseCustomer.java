package com.bantads.orchestration.bantadsorchestration.services.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SelfregistrationResponseCustomer {
	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = "orchestration-response-customer")
	public void receiveRead(@Payload String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			String action = node.get("action").asText();
			
			String id = node.get("manager").get("id").asText();
			
			if (action.equals("customer-ok")) {
				System.out.println("orchestration-response-customer(" + id + "): OK");
			} else if (action.equals("customer-update-ok")) {
				System.out.println("orchestration-response-customer(" + id + "): ATUALIZADO");
			} else if (action.equals("customer-delete-ok")) {
				System.out.println("orchestration-response-customer(" + id + "): DELETADO");
			} else if (action.equals("customer-duplicated")) {
				System.out.println("orchestration-response-customer(" + id + "): DUPLICADO");
			} else if (action.equals("customer-delete-error")) {
				//CASO DE ROLLBACK
				System.out.println("orchestration-response-customer(" + id + "): ERRO AO DELETAR");
			}else if (action.equals("customer-error")) {
				//CASO DE ROLLBACK
				System.out.println("orchestration-response-customer(" + id + "): ERRO GENÉRICO");
			}else if (action.equals("customer-update-error")) {
				System.out.println("orchestration-response-customer(" + id + "): ERRO NO UPDATE");
			} else {
				System.out.println("orchestration-response-customer(" + id + "): ACTION DESCONHECIDA - " + action);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
