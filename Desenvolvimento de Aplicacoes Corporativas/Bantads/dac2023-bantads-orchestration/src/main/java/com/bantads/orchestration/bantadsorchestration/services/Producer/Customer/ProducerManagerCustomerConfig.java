package com.bantads.orchestration.bantadsorchestration.services.Producer.Customer;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerManagerCustomerConfig {
    // @Value("orchestration-selfregistration-customer-manager")
    // private String queueManagerCustomer;

    @Bean
    @Qualifier("orchestration-selfregistration-customer-manager")
    public Queue queueManagerCustomer(){
        return new Queue("orchestration-selfregistration-customer-manager", true);
    }
}
