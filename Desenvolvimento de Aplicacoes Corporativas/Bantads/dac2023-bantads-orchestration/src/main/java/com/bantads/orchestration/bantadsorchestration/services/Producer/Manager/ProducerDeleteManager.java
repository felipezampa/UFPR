package com.bantads.orchestration.bantadsorchestration.services.Producer.Manager;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerDeleteManager {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    @Qualifier("delete-manager")
    private Queue queueDeleteManager;

    @Autowired
    private ObjectMapper objectMapper;

    public void objectMapper(UUID id) throws JsonProcessingException {
        this.template.convertAndSend(this.queueDeleteManager.getName(), id);
    }
}
