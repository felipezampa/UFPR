package com.bantads.orchestration.bantadsorchestration;

import org.modelmapper.ModelMapper;
import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerDeleteManagerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerManagerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Auth.ProducerAuth;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Customer.ProducerCustomer;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Customer.ProducerManagerCustomer;

import com.bantads.orchestration.bantadsorchestration.services.Producer.Manager.ProducerDeleteManager;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Manager.ProducerManager;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableRabbit
@SpringBootApplication
public class BantadsOrchestrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BantadsOrchestrationApplication.class, args);
	}

	@Bean    
	@Primary
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean    
	@Primary
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean    
	@Primary
	public ProducerCustomer producerC() {
		return new ProducerCustomer();
	}

	@Bean    
	@Primary
	public ProducerAuth producerA() {
		return new ProducerAuth();
	}

	@Bean    
	@Primary
	public ProducerManager producerG() {
		return new ProducerManager();
	}

	@Bean    
	@Primary
	public ProducerManagerAccount producerGC() {
		return new ProducerManagerAccount();
	}


	@Bean    
	@Primary
	public ProducerManagerCustomer producerMC() {
		return new ProducerManagerCustomer();
	}
	
	@Bean    
	@Primary
	public ProducerDeleteManager producerDg(){
		return new ProducerDeleteManager();
	}

	@Bean    
	@Primary
	public ProducerDeleteManagerAccount producerDgc(){
		return new ProducerDeleteManagerAccount();
	}

	
	 @Bean
	    public JavaMailSenderImpl mailSender() {
	        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

	        javaMailSender.setProtocol("smtp");
	        javaMailSender.setHost("smtp.gmail.com");
	        javaMailSender.setPort(587);
	        javaMailSender.setUsername("cafefic@gmail.com");
	        javaMailSender.setPassword("fzxngtudwxutapxx");
	        
	        Properties props = javaMailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "false");
	        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

	        
	        return javaMailSender;
	        
	    }
	
}
