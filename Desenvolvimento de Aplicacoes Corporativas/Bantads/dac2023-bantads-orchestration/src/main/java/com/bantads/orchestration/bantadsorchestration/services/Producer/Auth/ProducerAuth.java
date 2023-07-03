package com.bantads.orchestration.bantadsorchestration.services.Producer.Auth;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerCreateDTO;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Account.TransferManagerCreate;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferUpdateAuth;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Customer.TransferCustomerManager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferDeleteAuth;

@Component
public class ProducerAuth {
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queueAuth;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(RabbitCustomerDTO usuario) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(usuario);
		this.template.convertAndSend(this.queueAuth.getName(), json);
	}

	public void sendAndReceive(RabbitCustomerDTO usuario, String action) throws JsonProcessingException {
		TransferAuth transferAuth = new TransferAuth(usuario, action);
		this.template.convertAndSend(this.queueAuth.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(RabbitCustomerUpdateDTO usuario, String action) throws JsonProcessingException {
		TransferUpdateAuth transferAuth = new TransferUpdateAuth(usuario, action);
		this.template.convertAndSend(this.queueAuth.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(CustomerDeleteDTO usuario, String action) throws JsonProcessingException {
		TransferDeleteAuth transferAuth = new TransferDeleteAuth(usuario, action);
		this.template.convertAndSend(this.queueAuth.getName(), objectMapper.writeValueAsString(transferAuth));
	}
	
	public void sendAndReceive(RabbitCustomerManagerDTO usuario, String action) throws JsonProcessingException {
		TransferCustomerManager transferAuth = new TransferCustomerManager(usuario, action);
		this.template.convertAndSend(this.queueAuth.getName(),
				objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(ManagerCreateDTO usuario, String action) throws JsonProcessingException, AmqpException {
		TransferManagerCreate transferAuth = new TransferManagerCreate(usuario, action);
		this.template.convertAndSend(this.queueAuth.getName(),
				objectMapper.writeValueAsString(transferAuth));		
	}

}
