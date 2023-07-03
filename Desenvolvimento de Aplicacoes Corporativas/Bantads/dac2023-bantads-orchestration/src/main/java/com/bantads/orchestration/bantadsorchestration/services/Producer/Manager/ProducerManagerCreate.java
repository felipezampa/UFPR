package com.bantads.orchestration.bantadsorchestration.services.Producer.Manager;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;
import com.bantads.orchestration.bantadsorchestration.model.Manager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Manager.TransferManager;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Auth.TransferAuthManager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProducerManagerCreate {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    public void send(Manager manager) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(manager);
        this.template.convertAndSend(this.queueManager.getName(), json);
    }

    public void sendAndReceive(RabbitManagerDTO usuario, String action) throws JsonProcessingException {
        TransferAuthManager transferAuth = new TransferAuthManager(usuario, action);
        	 this.template.convertSendAndReceive(this.queueManager.getName(),
                objectMapper.writeValueAsString(transferAuth));

//        if (env.getProperty("bantads.rabbit.mock-manager-response").equals("true")) {
//            response = objectMapper.readValue(
//                    "{\"manager\":{\"id\":\"254d01fa-4e07-4a81-ab52-e1bbb9fc3a16\",\"idManager\":\"7e5145ec-f8fd-4190-a01d-cef5e1f3d56d\"},\"action\":\"manager-ok\"}",
//                    TransferManager.class);
//        }
    }
}
