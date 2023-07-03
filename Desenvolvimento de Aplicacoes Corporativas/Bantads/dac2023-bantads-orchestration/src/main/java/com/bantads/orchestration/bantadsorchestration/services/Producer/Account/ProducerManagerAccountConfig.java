package com.bantads.orchestration.bantadsorchestration.services.Producer.Account;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerManagerAccountConfig {
 
    @Bean
    @Qualifier("orchestration-selfregistration-customer-manager")
    public Queue queueAccountManager(){
        return new Queue("orchestration-selfregistration-account-manager", true);
    }
}
