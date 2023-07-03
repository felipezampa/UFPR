package com.bantads.orchestration.bantadsorchestration.services.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomerApproveConsumer {
    @Autowired
    private ObjectMapper objectMapper;
   

//    @RabbitListener(queues = "orchestration-response-auth")
//    public void receiveRead(@Payload String json) {
//        try {
//        	
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
