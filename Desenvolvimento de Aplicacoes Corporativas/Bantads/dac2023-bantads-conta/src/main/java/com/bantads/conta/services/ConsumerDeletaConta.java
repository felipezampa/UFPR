package com.bantads.conta.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.conta.model.R.ContaR;
import com.bantads.conta.repository.R.ContaRRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerDeletaConta {
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ContaRRepository contaRRepository;

    @RabbitListener(queues = "conta-deleta")
    public void receiveRead(@Payload String json) {
        try {
            ContaR conta = objectMapper.readValue(json, ContaR.class);
            contaRRepository.delete(conta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
