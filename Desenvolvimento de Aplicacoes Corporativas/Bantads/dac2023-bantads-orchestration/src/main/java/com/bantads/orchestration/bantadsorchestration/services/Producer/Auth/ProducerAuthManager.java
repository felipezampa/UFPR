package com.bantads.orchestration.bantadsorchestration.services.Producer.Auth;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;

import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferAuthManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferUpdateAuth;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferDeleteAuth;

@Component
public class ProducerAuthManager {
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queueAuthManager;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(RabbitManagerDTO usuario) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(usuario);
		this.template.convertAndSend(this.queueAuthManager.getName(), json);
	}

	public void sendAndReceive(RabbitManagerDTO usuario, String action) throws JsonProcessingException {
		TransferAuthManager transferAuth = new TransferAuthManager(usuario, action);
		this.template.convertAndSend(this.queueAuthManager.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(RabbitCustomerUpdateDTO usuario, String action) throws JsonProcessingException {
		TransferUpdateAuth transferAuth = new TransferUpdateAuth(usuario, action);
		this.template.convertAndSend(this.queueAuthManager.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(CustomerDeleteDTO usuario, String action) throws JsonProcessingException {
		TransferDeleteAuth transferAuth = new TransferDeleteAuth(usuario, action);
		this.template.convertAndSend(this.queueAuthManager.getName(), objectMapper.writeValueAsString(transferAuth));
	}

}
