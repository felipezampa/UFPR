package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.conta.DTOs.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerInsereUser {
	@Autowired                              
	private RabbitTemplate template;        
	                                        
	@Autowired                              
	private Queue queueInserirUser;
	
    @Autowired
    private ObjectMapper objectMapper;
      
	public void sendInserir(UserDTO user) throws JsonProcessingException {           
		String json = objectMapper.writeValueAsString(user);
	    this.template.convertAndSend(this.queueInserirUser.getName(), json);               
	}
}
