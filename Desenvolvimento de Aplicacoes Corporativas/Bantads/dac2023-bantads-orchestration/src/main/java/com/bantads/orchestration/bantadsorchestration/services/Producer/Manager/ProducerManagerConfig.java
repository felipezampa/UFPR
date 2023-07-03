package com.bantads.orchestration.bantadsorchestration.services.Producer.Manager;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerManagerConfig {
    @Value("orchestration-manager-register")
    private String queueManagerRegister;

    @Bean
    public Queue queueManagerRegister() {
        return new Queue(queueManagerRegister, true);
    }
}
