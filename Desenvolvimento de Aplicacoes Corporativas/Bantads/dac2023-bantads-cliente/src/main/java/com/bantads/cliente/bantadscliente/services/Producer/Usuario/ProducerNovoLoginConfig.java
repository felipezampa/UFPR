package com.bantads.cliente.bantadscliente.services.Producer.Usuario;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerNovoLoginConfig {
    @Value("cliente-autocadastro")
    private String queueAutoCadastro;

    @Bean
    public Queue queueAutoCadastro(){
        return new Queue(queueAutoCadastro);
    }
}
