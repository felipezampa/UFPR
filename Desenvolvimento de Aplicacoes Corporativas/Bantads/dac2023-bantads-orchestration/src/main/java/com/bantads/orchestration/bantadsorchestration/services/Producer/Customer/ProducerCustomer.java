package com.bantads.orchestration.bantadsorchestration.services.Producer.Customer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.model.Customer;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferAuth;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferUpdateAuth;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferDeleteAuth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerCustomer {
	@Autowired
	private RabbitTemplate template;

	@Autowired
	@Qualifier("orchestration-selfregistration-customer")
	private Queue queueCustomer;

	@Autowired
	private ObjectMapper objectMapper;

	public void send(RabbitCustomerDTO customer) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(customer);
		this.template.convertAndSend(this.queueCustomer.getName(), json);
	}

	public void sendAndReceive(RabbitCustomerDTO usuario, String action) throws JsonProcessingException {
		TransferAuth transferAuth = new TransferAuth(usuario, action);
		this.template.convertAndSend(this.queueCustomer.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(RabbitCustomerUpdateDTO usuario, String action) throws JsonProcessingException {
		TransferUpdateAuth transferAuth = new TransferUpdateAuth(usuario, action);
		this.template.convertAndSend(this.queueCustomer.getName(), objectMapper.writeValueAsString(transferAuth));
	}

	public void sendAndReceive(CustomerDeleteDTO usuario, String action) throws JsonProcessingException {
		TransferDeleteAuth transferAuth = new TransferDeleteAuth(usuario, action);
		this.template.convertAndSend(this.queueCustomer.getName(), objectMapper.writeValueAsString(transferAuth));
	}

}
