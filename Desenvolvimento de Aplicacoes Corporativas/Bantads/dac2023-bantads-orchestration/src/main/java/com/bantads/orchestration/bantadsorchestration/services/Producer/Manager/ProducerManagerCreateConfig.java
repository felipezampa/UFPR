package com.bantads.orchestration.bantadsorchestration.services.Producer.Manager;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerManagerCreateConfig {
    @Value("orchestration-selfregistration-manager")
    private String queueManager;

    @Bean
    public Queue queueManager() {
        return new Queue(queueManager, true);
    }
}
