package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerDeletaContaConfig {
	@Value("conta-deleta")
    private String queueDeletaConta;
	
	@Bean
    public Queue queueDeletaConta(){
        return new Queue(queueDeletaConta);
    }
}
