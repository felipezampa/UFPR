package com.bantads.cliente.bantadscliente.services.Consumer.Autocadastro;

import com.bantads.cliente.bantadscliente.model.Cliente;
import com.bantads.cliente.bantadscliente.model.Endereco;
import com.bantads.cliente.bantadscliente.model.Estado;
import com.bantads.cliente.bantadscliente.repository.ClienteRepository;
import com.bantads.cliente.bantadscliente.services.Producer.Rollback.SenderAutenticacao;
import com.bantads.cliente.bantadscliente.services.Producer.Usuario.ProducerNovaConta;
import com.bantads.cliente.bantadscliente.services.rabbitMQ.RabbitMqController;
import com.bantads.cliente.bantadscliente.services.Cliente.ClienteService;

import java.util.UUID;
import java.math.BigDecimal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerAutoCadastro {
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private SenderAutenticacao senderAutenticacao;
    
    @Autowired
    private RabbitMqController rabbitMqController;

    @Autowired
    private ClienteService clienteService;

    @RabbitListener(queues = "orchestration-selfregistration-customer")
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        try {        	      
        	System.out.println("ConsumerAutoCadastro -> receive");
        	
            // Extrai o payload
        	ObjectMapper mapper = new ObjectMapper();
        	JsonNode node = mapper.readTree(json);

        	String action = node.get("action").asText();
        	
            if (action.equals("customer-register")) {
            	clienteService.RegistrarCliente(node);
            }
        	else if (action.equals("customer-update")) {
        		clienteService.AtualizarCliente(node); 
        	}
        	else if(action.equals("customer-delete")){
        		clienteService.DeletarCliente(node);
            } else {
                rabbitMqController.enviarMensagem(node, "customer-error", "ERROR");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
