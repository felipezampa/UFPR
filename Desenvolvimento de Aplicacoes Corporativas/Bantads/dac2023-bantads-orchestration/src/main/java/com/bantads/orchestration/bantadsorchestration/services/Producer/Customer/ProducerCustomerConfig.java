package com.bantads.orchestration.bantadsorchestration.services.Producer.Customer;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerCustomerConfig {
//    @Value("orchestration-orchestration-selfregistration-customer")
//    private String queueCliente;

    @Bean
    @Qualifier("orchestration-selfregistration-customer")
    public Queue queueCliente() {
        return new Queue("orchestration-selfregistration-customer", true);
    }
}
