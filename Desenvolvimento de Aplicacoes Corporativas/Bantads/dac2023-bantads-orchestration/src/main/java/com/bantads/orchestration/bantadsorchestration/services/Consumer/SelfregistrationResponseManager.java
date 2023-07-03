package com.bantads.orchestration.bantadsorchestration.services.Consumer;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerCreateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.ResponseClienteSagaDTO;
import com.bantads.orchestration.bantadsorchestration.mapper.RabbitCustomerMapper;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerManagerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Auth.ProducerAuth;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Customer.ProducerCustomer;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Customer.ProducerManagerCustomer;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Manager.ProducerManager;
import com.bantads.orchestration.bantadsorchestration.utils.MailManagerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SelfregistrationResponseManager {
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProducerCustomer producerCustomer;

	@Autowired
	private ProducerAuth producerAutenticacao;

	@Autowired
	private ProducerAccount producerAccount;

	@Autowired
	private ProducerManager producerManager;
	@Autowired
	private ProducerManagerCustomer producerManagerCustomer;

	@Autowired
	private ProducerManagerAccount producerManagerAccount;

	@Autowired
	private ProducerAuth producerAuth;

	@Autowired
	private MailManagerService mailService;

	@Autowired
	private Environment env;

	@RabbitListener(queues = "orchestration-response-manager")
	public void receiveRead(@Payload String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			System.out.println(json);
			String action = node.get("action").asText();
			if (action.equals("manager-customer-ok")) {
				String id = node.get("manager").get("id").asText();
				String idManager = node.get("manager").get("idManager").asText();

				// EXIGIR EMAIL DE MS GERENTE
				// AJUSTAR ROTA
				System.out.println("orchestration-response-manager(" + id + "): OK");
				RabbitCustomerManagerDTO dto = new RabbitCustomerManagerDTO(id, idManager);
				producerManagerCustomer.sendAndReceive(dto, "manager-customer-approve");

				producerManagerAccount.sendAndReceive(dto, "account-manager");
				Thread.sleep(6000);

				try {
					final String uri = env.getProperty("bantads.customer-link") + "/clientes/saga/" + id;

					RestTemplate restTemplate = new RestTemplate();
					String result = restTemplate.getForObject(uri, String.class);
					System.out.println(result);
					ResponseClienteSagaDTO responseDTO = mapper.readValue(result, ResponseClienteSagaDTO.class);
					UUID random = UUID.randomUUID();
					String passwordGenerated = random.toString().replaceAll("_", "").substring(0, 4);
					mailService.sendMail("cafefic@gmail.com", responseDTO.getEmail(), "Sua senha Bantads Chegou!",
							"Seja bem vindo ao Bantads! Sua senha de acesso é: " + passwordGenerated);

					// PARA AUTH
					dto.setPassword(passwordGenerated);
					producerAuth.sendAndReceive(dto, "manager-customer-approve");

				} catch (Exception e) {
					System.out.println(e);
				}

			} else if (action.equals("gerente-auth-create")) {

				String idManager = node.get("id").asText();
				String nome = node.get("nome").asText();
				String email = node.get("email").asText();
				UUID random = UUID.randomUUID();
				String passwordGenerated = random.toString().replaceAll("_", "").substring(0, 4);
				// EXIGIR EMAIL DE MS GERENTE
				// AJUSTAR ROTA
				ManagerCreateDTO dto = new ManagerCreateDTO(idManager, nome, email, passwordGenerated);

				try {

					mailService.sendMail("cafefic@gmail.com", dto.getEmail(), "Gerente, sua senha Bantads Chegou!",
							"Seja bem vindo ao Bantads! Sua senha de acesso é: " + passwordGenerated);

					// PARA AUTH
					dto.setPassword(passwordGenerated);
					producerAuth.sendAndReceive(dto, "manager-auth-create");

				} catch (Exception e) {
					System.out.println(e);
				}

			} else if (action.equals("gerente-auth-update")) {
				String idManager = node.get("id").asText();
				String nome = node.get("nome").asText();
				String email = node.get("email").asText();
				UUID random = UUID.randomUUID();
				String passwordGenerated = random.toString().replaceAll("_", "").substring(0, 4);

				ManagerCreateDTO dto = new ManagerCreateDTO(idManager, nome, email, passwordGenerated);

				producerAuth.sendAndReceive(dto, "manager-auth-update");

			} else if (action.equals("gerente-auth-delete")) {
				String idManager = node.get("id").asText();
				String nome = node.get("nome").asText();
				String email = node.get("email").asText();
				UUID random = UUID.randomUUID();
				String passwordGenerated = random.toString().replaceAll("_", "").substring(0, 4);
				ManagerCreateDTO dto = new ManagerCreateDTO(idManager, nome, email, passwordGenerated);

				producerAuth.sendAndReceive(dto, "manager-auth-delete");

			} else if (action.equals("manager-customer-duplicated")) {
				String id = node.get("manager").get("id").asText();
				System.out.println("orchestration-response-manager(" + id + "): DUPLICADO");
			} else if (action.equals("manager-customer-error")) {
				String id = node.get("manager").get("id").asText();
				CustomerDeleteDTO customer;

				customer = RabbitCustomerMapper.map(id, modelMapper);

				try {
					final String uri = env.getProperty("bantads.customer-link") + "/clientes/saga/" + id;

					RestTemplate restTemplate = new RestTemplate();
					String result = restTemplate.getForObject(uri, String.class);

					ResponseClienteSagaDTO responseDTO = mapper.readValue(result, ResponseClienteSagaDTO.class);

					UUID random = UUID.randomUUID();
					mailService.sendMail("cafefic@gmail.com", responseDTO.getEmail(), "Aviso sobre sua conta Bantads",
							"Caro cliente, não foi possível criar seu usuário no Bantads. Verifique os dados informados, comunique o suporte e regularize sua situação.");

				} catch (Exception e) {
					System.out.println("orchestration-response-account(" + id + "): MANAGER ERROR");
				}

				producerAutenticacao.sendAndReceive(customer, "auth-delete");
				producerAccount.sendAndReceive(customer, "account-delete");
				producerCustomer.sendAndReceive(customer, "customer-delete");

				System.out.println("orchestration-response-manager(" + id + "): ERRO GENÉRICO");
			} else {
				System.out.println("orchestration-response-manager(): ACTION DESCONHECIDA - " + action);
			}
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Falha interna no tratamento de mensagens SelfRegistrationResponseManager");

		}
	}
}
