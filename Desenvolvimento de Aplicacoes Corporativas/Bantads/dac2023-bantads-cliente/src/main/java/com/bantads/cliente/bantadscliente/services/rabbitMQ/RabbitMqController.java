package com.bantads.cliente.bantadscliente.services.rabbitMQ;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqController {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqController.class);

    @Autowired
    public RabbitMqController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMensagem(JsonNode node, String action, String log) {
        ((ObjectNode) node).put("action", action);
        String novoJson = node.toString();
        logger.info("MENSAGEM ENVIADA PARA FILA - " + log);
        rabbitTemplate.convertAndSend("orchestration-response-customer", novoJson);
    }
}
