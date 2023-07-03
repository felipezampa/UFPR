package com.bantads.conta.services;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducerInsereMovimentacaoConfig {
	@Value("movimentacao-inserir")
    private String queueInserirMovimentacao;
	
	@Bean
    public Queue queueInserirMovimentacao(){
        return new Queue(queueInserirMovimentacao);
    }
}
