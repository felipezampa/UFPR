package com.bantads.orchestration.bantadsorchestration.services.Producer.Account;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerDeleteManagerAccountConfig {
    @Value("orchestration-delete-manager-account")
    private String queueDeleteGerenteConta;

    @Bean
    public Queue queueDeleteGerenteConta() {
        return new Queue(queueDeleteGerenteConta, true);
    }
}
