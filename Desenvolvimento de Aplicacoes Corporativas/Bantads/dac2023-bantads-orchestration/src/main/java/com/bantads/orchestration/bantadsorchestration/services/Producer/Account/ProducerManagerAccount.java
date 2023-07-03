package com.bantads.orchestration.bantadsorchestration.services.Producer.Account;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bantads.orchestration.bantadsorchestration.model.Manager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Manager.TransferManager;
import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerAccountDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerManagerDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerManagerAccount {
	@Autowired
	private RabbitTemplate template;

	@Autowired
	@Qualifier("orchestration-selfregistration-customer-manager")
	private Queue queueAccountManager;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(Manager manager) throws JsonProcessingException {
		ManagerAccountDTO managerContaDTO = new ManagerAccountDTO(manager.getId(), manager.getSaga());
		String json = objectMapper.writeValueAsString(managerContaDTO);
		this.template.convertAndSend(this.queueAccountManager.getName(), json);
	}

	public void sendAndReceive(RabbitCustomerManagerDTO usuario, String action) throws JsonProcessingException {
		TransferManager transferAuth = new TransferManager(usuario, action);
		this.template.convertAndSend(this.queueAccountManager.getName(), objectMapper.writeValueAsString(transferAuth));
	}
}
