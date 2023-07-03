package com.bantads.conta.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bantads.conta.DTOs.ContaDTO;
import com.bantads.conta.DTOs.ContaResponseDTO;
import com.bantads.conta.DTOs.ResponseDTO;
import com.bantads.conta.DTOs.ResponseUserDTO;
import com.bantads.conta.DTOs.UserDTO;
import com.bantads.conta.model.CUD.ContaCUD;
import com.bantads.conta.model.CUD.UserCUD;
import com.bantads.conta.model.R.ContaR;
import com.bantads.conta.model.R.UserR;
import com.bantads.conta.repository.CUD.ContaCUDRepository;
import com.bantads.conta.repository.CUD.MovimentacaoCUDRepository;
import com.bantads.conta.repository.CUD.UserCUDRepository;
import com.bantads.conta.repository.R.ContaRRepository;
import com.bantads.conta.repository.R.UserRRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SagaConsumer {

    @Autowired
    private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private UserCUDRepository userCUDRepository;
	
	@Autowired
	private UserRRepository userRRespository;
	
	@Autowired
	private ContaCUDRepository contaCUDRepository;
	
	@Autowired
	private ProducerInsereUser producerInsereUser;
	
	@Autowired
	private ProducerDeletaUser ProducerDeletaUser;
	
	@Autowired
	private ProducerInsereConta producerInsereConta;
	
	@Autowired
	private ProducerResponse producerResponse;
	
	@Autowired
	private ProducerInsereConta producerAtualizaConta;
	
	@Autowired
	private ProducerDeletaConta producerDeletaConta;
	
    @Autowired
    private ModelMapper mapper;
	
    @RabbitListener(queues = "orchestration-selfregistration-account")
    public void orchestrationSelfregistrationAccount(@Payload String json) {
        try {
        	JSONObject parsedJson = new JSONObject(json);    
        	String action = parsedJson.getString("action");
        	System.out.println("____________________________");
        	System.out.println(parsedJson);
        	
        	if (action.equals("account-register")) {
        		JSONObject user = parsedJson.getJSONObject("manager");
        		String id = user.getString("id");
        		String cpf = user.getString("cpf");
        		BigDecimal salario = new BigDecimal(user.getDouble("salario"));
        		
        		UserCUD usercud = new UserCUD(id, cpf, salario);
        		System.out.println("____________________________");
            	System.out.println(id + cpf);
            	
        		userCUDRepository.save(usercud);
        		
        		producerInsereUser.sendInserir(mapper.map(usercud, UserDTO.class));      		
        	} else if (action.equals("account-update")) {
        		JSONObject user = parsedJson.getJSONObject("manager");
        		String id = user.getString("id");
        		String cpf = user.getString("cpf");
        		BigDecimal salario = new BigDecimal(user.getDouble("salario"));
        		
        		UserCUD usercud = new UserCUD(id, cpf, salario);
        		UserCUD oldUser = userCUDRepository.findById(id).get();
        		
        		if (oldUser.getSalario().compareTo(usercud.getSalario()) != 0) {
        			ContaCUD contaCud = contaCUDRepository.findByIdCliente(id).get();
        			
    	           BigDecimal limite = new BigDecimal(0);
    	            
    	            if (usercud.getSalario().compareTo(new BigDecimal(2000)) >= 0)
    	            	limite = usercud.getSalario().divide(new BigDecimal(2));
    	            
    	            contaCud.setLimite(limite);
    	            
    	            contaCUDRepository.save(contaCud);
    	            
    	            producerAtualizaConta.sendInserir(mapper.map(contaCud, ContaResponseDTO.class));
        		}
        		userCUDRepository.save(usercud);
        		
        		producerInsereUser.sendInserir(mapper.map(usercud, UserDTO.class));  
        	} else if (action.equals("delete-account")) {
        		JSONObject userJson = parsedJson.getJSONObject("manager");
        		String id = userJson.getString("id");
        		
        		UserCUD user = userCUDRepository.findById(id).get();
        		ContaCUD conta = contaCUDRepository.findByIdCliente(id).get();

        		contaCUDRepository.delete(conta);
        		userCUDRepository.delete(user);
        		
        		producerDeletaConta.sendDelete(mapper.map(conta, ContaResponseDTO.class));
        		ProducerDeletaUser.sendDelete(mapper.map(user, UserDTO.class));
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RabbitListener(queues = "orchestration-selfregistration-account-manager")
    public void receiveRead(@Payload String json) {
        try {
        	System.out.print("orchestration-selfregistration-account-manager");
        	JSONObject parsedJson = new JSONObject(json);        
        	JSONObject userJson = parsedJson.getJSONObject("manager");
        	System.out.print(parsedJson);

            String id = userJson.getString("id");
            String idManager = userJson.getString("idManager");

            Optional<UserR> origemOpt = userRRespository.findById(id);
            if(origemOpt.isPresent()) {
            UserR user = origemOpt.get();
            
            BigDecimal limite = new BigDecimal(0);
            
            if (user.getSalario().compareTo(new BigDecimal(2000)) >= 0)
            	limite = user.getSalario().divide(new BigDecimal(2));
            
            ContaDTO contaDto = new ContaDTO(user.getId(), idManager, new BigDecimal(0), limite);
            ContaCUD contaCud = new ContaCUD(contaDto);
            
            contaCUDRepository.save(contaCud);
            
            producerInsereConta.sendInserir(mapper.map(contaCud, ContaResponseDTO.class));
            ResponseUserDTO responseUser = new ResponseUserDTO(user.getId());
            ResponseDTO responseDTO = new ResponseDTO(responseUser, "account-manager-ok");
            producerResponse.sendMessage(responseDTO);

            }else {
				ResponseUserDTO responseUser = new ResponseUserDTO(id);
				ResponseDTO responseDTO = new ResponseDTO(responseUser, "account-manager-error");
				producerResponse.sendMessage(responseDTO);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
