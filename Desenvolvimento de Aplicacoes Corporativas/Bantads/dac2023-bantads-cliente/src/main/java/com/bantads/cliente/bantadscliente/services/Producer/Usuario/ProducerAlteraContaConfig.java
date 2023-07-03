package com.bantads.cliente.bantadscliente.services.Producer.Usuario;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerAlteraContaConfig {
    @Value("cliente-alteraconta")
    private String queueAlteraConta;

    @Bean
    public Queue queueAlteraConta(){
        return new Queue(queueAlteraConta);
    }
}
