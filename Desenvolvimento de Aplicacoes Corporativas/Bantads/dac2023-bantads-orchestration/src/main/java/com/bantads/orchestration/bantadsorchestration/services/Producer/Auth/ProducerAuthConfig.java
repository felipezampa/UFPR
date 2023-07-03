package com.bantads.orchestration.bantadsorchestration.services.Producer.Auth;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerAuthConfig {
    @Value("orchestration-selfregistration-auth")
    private String queueAuth;

    @Bean
    public Queue queueAuth(){
        return new Queue(queueAuth, true);
    }
}
