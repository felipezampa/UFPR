package com.bantads.orchestration.bantadsorchestration.services.Producer.Auth;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerAuthManagerConfig {
    @Value("orchestration-managerregistration-auth")
    private String queueAuthManager;

    @Bean
    public Queue queueAuthManager(){
        return new Queue(queueAuthManager, true);
    }
}
