package com.bantads.cliente.bantadscliente.services.Producer.Usuario;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerAlteraLoginConfig {
    @Value("cliente-alteralogin")
    private String queueAlteraLogin;

    @Bean
    public Queue queueAlteraLogin(){
        return new Queue(queueAlteraLogin);
    }
}
