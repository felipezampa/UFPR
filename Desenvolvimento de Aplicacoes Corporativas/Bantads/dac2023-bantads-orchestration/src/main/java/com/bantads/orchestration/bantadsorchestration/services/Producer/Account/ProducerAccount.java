package com.bantads.orchestration.bantadsorchestration.services.Producer.Account;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;

import com.bantads.orchestration.bantadsorchestration.model.Manager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Account.TransferAccount;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Account.TransferUpdateAccount;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Account.TransferDeleteAccount;
import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerAccountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerAccount {
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private Queue queueAccount;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(Manager manager) throws JsonProcessingException {
		ManagerAccountDTO managerContaDTO = new ManagerAccountDTO(manager.getId(), manager.getSaga());
		String json = objectMapper.writeValueAsString(managerContaDTO);
		this.template.convertAndSend(this.queueAccount.getName(), json);
	}

	public void sendAndReceive(RabbitCustomerDTO usuario, String action) throws JsonProcessingException {
		TransferAccount transferAuth = new TransferAccount(usuario, action);
		this.template.convertAndSend(this.queueAccount.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(RabbitCustomerUpdateDTO usuario, String action) throws JsonProcessingException {
		TransferUpdateAccount transferAuth = new TransferUpdateAccount(usuario, action);
		this.template.convertAndSend(this.queueAccount.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(CustomerDeleteDTO usuario, String action) throws JsonProcessingException {
		TransferDeleteAccount transferAuth = new TransferDeleteAccount(usuario, action);
		this.template.convertAndSend(this.queueAccount.getName(), objectMapper.writeValueAsString(transferAuth));

	}
}
