package com.bantads.orchestration.bantadsorchestration.services.Consumer;

import java.util.Arrays;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import com.bantads.orchestration.bantadsorchestration.DTOs.ResponseClienteSagaDTO;
import com.bantads.orchestration.bantadsorchestration.utils.MailManagerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SelfregistrationResponseAccount {
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MailManagerService mailService;
	
	@Autowired
	private Environment env;

	@RabbitListener(queues = "orchestration-response-account")
	public void receiveRead(@Payload String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			System.out.println(json);
			String action = node.get("action").asText();
			String id = node.get("manager").get("id").asText();
			if (action.equals("account-ok")) {
				System.out.println("orchestration-response-account(" + id + "): OK");
			} else if (action.equals("account-manager-ok")) {
				System.out.println("orchestration-response-account(" + id + "): ACC-MANAGEROK");
			} else if (action.equals("account-manager-error")) {
				
				System.out.println("orchestration-response-account(" + id + "): ACC-MANAGER ERROR");
				
				try {
					   final String uri = env.getProperty("bantads.customer-link") + "/clientes/saga/" + id;

					    RestTemplate restTemplate = new RestTemplate();
					    String result = restTemplate.getForObject(uri, String.class);

						ResponseClienteSagaDTO responseDTO =  mapper.readValue(result, ResponseClienteSagaDTO.class);

					    
					UUID random = UUID.randomUUID();
					mailService.sendMail("cafefic@gmail.com", responseDTO.getEmail(), "Aviso sobre sua conta Bantads",
							"Caro cliente, não foi possível criar sua conta no Bantads. Comunique o suporte e regularize sua situação.");
					
				} catch (Exception e) {
					System.out.println("orchestration-response-account(" + id + "): EMAIL ACC-MANAGER ERROR");
				}
			} else if (action.equals("account-duplicated")) {
				System.out.println("orchestration-response-account(" + id + "): DUPLICADO");
			} else if (action.equals("account-error")) {
				try {
					   final String uri = env.getProperty("bantads.customer-link") + "/clientes/saga";

					    RestTemplate restTemplate = new RestTemplate();
					    String result = restTemplate.getForObject(uri, String.class);

						ResponseClienteSagaDTO responseDTO =  mapper.readValue(result, ResponseClienteSagaDTO.class);

					    
					UUID random = UUID.randomUUID();
					mailService.sendMail("cafefic@gmail.com", responseDTO.getEmail(), "Aviso sobre sua conta Bantads",
							"Caro cliente, não foi possível criar sua conta no Bantads. Comunique o suporte e regularize sua situação.");
					
				} catch (Exception e) {
					System.out.println("orchestration-response-account(" + id + "): EMAIL ACC-MANAGER ERROR");
				}
				
				System.out.println("orchestration-response-account(" + id + "): ERRO GENÉRICO");
			} else {
				System.out.println("orchestration-response-account(" + id + "): ACTION DESCONHECIDA -" + action);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
