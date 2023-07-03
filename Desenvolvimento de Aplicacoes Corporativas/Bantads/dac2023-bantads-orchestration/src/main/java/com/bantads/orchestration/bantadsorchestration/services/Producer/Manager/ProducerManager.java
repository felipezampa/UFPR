package com.bantads.orchestration.bantadsorchestration.services.Producer.Manager;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.model.Manager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Manager.TransferManager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferAuth;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferDeleteAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerManager {
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queueManagerRegister;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private Environment env;

	public void send(Manager manager) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(manager);
		this.template.convertAndSend(this.queueManagerRegister.getName(), json);
	}

	public void sendAndReceive(RabbitCustomerDTO usuario, String action) throws JsonProcessingException {
		TransferAuth transferAuth = new TransferAuth(usuario, action);
		this.template.convertSendAndReceive(this.queueManagerRegister.getName(),
				objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(CustomerDeleteDTO usuario, String action) throws JsonProcessingException {
		TransferDeleteAuth transferAuth = new TransferDeleteAuth(usuario, action);
		this.template.convertAndSend(this.queueManagerRegister.getName(), objectMapper.writeValueAsString(transferAuth));
	}

}
