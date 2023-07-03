package com.bantads.orchestration.bantadsorchestration.services.Producer.Manager;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerDeleteManagerConfig {
    @Value("orchestration-delete-manager")
    private String queueDeleteManager;

    @Bean  
    @Qualifier("delete-manager")
    public Queue queueDeleteManager() {
        return new Queue(queueDeleteManager, true);
    }
}
