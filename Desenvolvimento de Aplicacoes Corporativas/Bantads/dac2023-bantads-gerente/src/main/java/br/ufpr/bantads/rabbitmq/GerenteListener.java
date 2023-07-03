package br.ufpr.bantads.rabbitmq;

import br.ufpr.bantads.model.dto.*;
import br.ufpr.bantads.model.entity.Gerente;
import br.ufpr.bantads.service.GerenteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class GerenteListener {

    @Autowired
    GerenteService gerenteService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private GerenteProducer gerenteProducer;

    @RabbitListener(queues = "orchestration-manager-register")
    public void receiveMessage(@Payload String json) throws JsonProcessingException {
        try {
            // Converte o payload em um JsonNode (conteúdo do payload)
            ObjectMapper mapper = new ObjectMapper();
            // Extrai a ação do payload
            JsonNode node = mapper.readTree(json);
            String action = node.get("action").asText();

            if (action.equals("manager-register")) {
                gerenteService.criarGerenteCliente(node);
            } else if (action.equals("manager-update")) {
                gerenteService.updateGerenteCliente(node);
            } else if (action.equals("manager-delete")) {
                gerenteService.removerGerenteCliente(node);
            } else {
                gerenteProducer.sendMessage(node, "manager-error", "ERROR");
            }
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            gerenteProducer.sendMessage(node, "manager-error", "ERROR");
            e.printStackTrace();
        }
    }

}
