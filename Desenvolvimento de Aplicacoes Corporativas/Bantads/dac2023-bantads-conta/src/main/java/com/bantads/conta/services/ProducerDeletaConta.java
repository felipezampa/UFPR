package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.bantads.conta.DTOs.ContaResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Primary
public class ProducerDeletaConta {
	@Autowired                              
	private RabbitTemplate template;        
	                                        
	@Autowired                              
	private Queue queueDeletaConta;
	
    @Autowired
    private ObjectMapper objectMapper;
      
	public void sendDelete(ContaResponseDTO conta) throws JsonProcessingException {           
		String json = objectMapper.writeValueAsString(conta);
	    this.template.convertAndSend(this.queueDeletaConta.getName(), json);               
	}
}
