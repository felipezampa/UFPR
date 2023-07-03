package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerDeletaUserConfig {
	@Value("user-deleta")
    private String queueDeletaUser;
	
	@Bean
    public Queue queueDeletaUser(){
        return new Queue(queueDeletaUser);
    }
}
