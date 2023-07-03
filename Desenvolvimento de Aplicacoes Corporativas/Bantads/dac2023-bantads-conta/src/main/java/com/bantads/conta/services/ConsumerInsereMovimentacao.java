package com.bantads.conta.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bantads.conta.model.R.MovimentacaoR;
import com.bantads.conta.repository.R.MovimentacaoRRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConsumerInsereMovimentacao {
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private MovimentacaoRRepository movimentacaoRRepository;

    @RabbitListener(queues = "movimentacao-inserir")
    public void receiveRead(@Payload String json) {
        try {
            MovimentacaoR movimentacao = objectMapper.readValue(json, MovimentacaoR.class);
            movimentacaoRRepository.save(movimentacao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
