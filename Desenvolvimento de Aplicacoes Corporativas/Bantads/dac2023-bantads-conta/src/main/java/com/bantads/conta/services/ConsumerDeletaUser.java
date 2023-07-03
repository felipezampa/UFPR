package com.bantads.conta.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.conta.model.R.UserR;
import com.bantads.conta.repository.R.UserRRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerDeletaUser {
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private UserRRepository userRRepository;

    @RabbitListener(queues = "user-deleta")
    public void receiveRead(@Payload String json) {
        try {
            UserR user = objectMapper.readValue(json, UserR.class);
            userRRepository.delete(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
