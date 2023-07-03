package br.ufpr.bantads.rabbitmq;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerenteProducer {

    private final RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(GerenteProducer.class);

    @Autowired
    public GerenteProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(JsonNode node, String action, String log) {
        ((ObjectNode) node).put("action", action);
        String novoJson = node.toString();
        logger.info("MENSAGEM ENVIADA PARA FILA - " + log);
        rabbitTemplate.convertAndSend("orchestration-response-manager", novoJson);
    }
}
