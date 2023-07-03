package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerAtualizaContaConfig {
	@Value("conta-atualiza")
    private String queueAtualizaConta;
	
	@Bean
    public Queue queueAtualizaConta(){
        return new Queue(queueAtualizaConta);
    }
}
