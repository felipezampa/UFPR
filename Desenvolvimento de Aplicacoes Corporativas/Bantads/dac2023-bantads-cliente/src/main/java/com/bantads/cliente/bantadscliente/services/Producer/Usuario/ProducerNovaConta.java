package com.bantads.cliente.bantadscliente.services.Producer.Usuario;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.cliente.bantadscliente.DTOs.RabbitContaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerNovaConta {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueNovaConta;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(RabbitContaDTO novaConta) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(novaConta);
        this.template.convertAndSend(this.queueNovaConta.getName(), json);
    }
}
