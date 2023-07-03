package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerResponseConfig {
	@Value("orchestration-response-account")
    private String queueResponse;
	
	@Bean
    public Queue queueResponse(){
        return new Queue(queueResponse);
    }
}
