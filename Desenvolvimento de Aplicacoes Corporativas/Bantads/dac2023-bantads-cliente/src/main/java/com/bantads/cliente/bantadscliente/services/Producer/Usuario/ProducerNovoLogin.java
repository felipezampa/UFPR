package com.bantads.cliente.bantadscliente.services.Producer.Usuario;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bantads.cliente.bantadscliente.DTOs.RabbitLoginDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerNovoLogin{
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueAutoCadastro;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(RabbitLoginDTO novoLoginDTO) throws JsonProcessingException{
        String json = objectMapper.writeValueAsString(novoLoginDTO);
        this.template.convertAndSend(this.queueAutoCadastro.getName(), json);
    }
}
