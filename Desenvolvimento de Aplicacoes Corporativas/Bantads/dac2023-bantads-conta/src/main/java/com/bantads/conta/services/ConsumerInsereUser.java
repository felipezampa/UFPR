package com.bantads.conta.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bantads.conta.model.R.MovimentacaoR;
import com.bantads.conta.model.R.UserR;
import com.bantads.conta.repository.R.MovimentacaoRRepository;
import com.bantads.conta.repository.R.UserRRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConsumerInsereUser {
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private UserRRepository userRRepository;

    @RabbitListener(queues = "user-inserir")
    public void receiveRead(@Payload String json) {
        try {
            UserR user = objectMapper.readValue(json, UserR.class);
            userRRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
