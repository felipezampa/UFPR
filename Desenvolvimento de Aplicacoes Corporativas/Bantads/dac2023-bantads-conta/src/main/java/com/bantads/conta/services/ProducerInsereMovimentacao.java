package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.conta.DTOs.MovimentacaoResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerInsereMovimentacao {
	@Autowired                              
	private RabbitTemplate template;        
	                                        
	@Autowired                              
	private Queue queueInserirMovimentacao;
	
    @Autowired
    private ObjectMapper objectMapper;
      
	public void sendInserir(MovimentacaoResponseDTO movimentacao) throws JsonProcessingException {           
		String json = objectMapper.writeValueAsString(movimentacao);
	    this.template.convertAndSend(this.queueInserirMovimentacao.getName(), json);               
	}
}
