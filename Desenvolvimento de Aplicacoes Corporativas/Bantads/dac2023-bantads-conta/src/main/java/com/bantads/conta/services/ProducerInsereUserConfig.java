package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerInsereUserConfig {
	@Value("user-inserir")
    private String queueInserirUser;
	
	@Bean
	//fila que o conta fala com ele mesmo para gerar o registro no CONTAR
    public Queue queueInserirUser(){
        return new Queue(queueInserirUser);
    }
}
