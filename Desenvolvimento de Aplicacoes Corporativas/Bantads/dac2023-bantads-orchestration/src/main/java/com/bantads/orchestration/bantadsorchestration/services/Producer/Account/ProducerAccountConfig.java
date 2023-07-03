package com.bantads.orchestration.bantadsorchestration.services.Producer.Account;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerAccountConfig {
    @Value("orchestration-selfregistration-account")
    private String queueAccount;

    @Bean
    public Queue queueAccount(){
        return new Queue(queueAccount, true);
    }
}
