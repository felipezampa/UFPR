package com.bantads.orchestration.bantadsorchestration.REST;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.mapper.RabbitCustomerMapper;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerManagerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Auth.ProducerAuth;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Customer.ProducerCustomer;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Customer.ProducerManagerCustomer;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Manager.ProducerManager;
import com.bantads.orchestration.bantadsorchestration.utils.JsonResponseObject;
import com.bantads.orchestration.bantadsorchestration.utils.MailManagerService;
import com.bantads.orchestration.bantadsorchestration.validator.SelfRegistrationValidator;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class SelfRegistrationREST {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProducerCustomer producerCustomer;

	@Autowired
	private ProducerAuth producerAutenticacao;

	@Autowired
	private ProducerAccount producerAccount;

	@Autowired
	private ProducerManager producerManager;

	@Autowired
	private ProducerManagerAccount producerManagerAccount;

	@Autowired
	private ProducerManagerCustomer producerManagerCustomer;

	@Autowired
	private MailManagerService mailService;
	
	@Autowired
	private Environment env;

	@PostMapping
	ResponseEntity<JsonResponseObject> cadastro(@RequestBody CustomerDTO customerDTO) {

		if (!SelfRegistrationValidator.validateCliente(customerDTO)

		) {
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Dados do cliente inválidos. Verifique as informações e tente novamente", null),
					HttpStatus.BAD_REQUEST);

		}
		if (!SelfRegistrationValidator.validateEndereco(customerDTO.getEndereco())) {
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Dados do endereço inválidos. Verifique as informações e tente novamente", null),
					HttpStatus.BAD_REQUEST);

		}

		RabbitCustomerDTO customer;
		try {
			UUID saga = UUID.randomUUID();

			// usuario.setTipoUsuario(UserType.Cliente);
			customer = RabbitCustomerMapper.map(customerDTO, saga, mapper);

			producerAutenticacao.sendAndReceive(customer, "auth-register");
			System.out.println("STAGE1 OK - CUSTOMER -> AUTH");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAuth.getAction().equals("auth-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário na autenticação. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerCustomer.sendAndReceive(customer, "customer-register");
			System.out.println("STAGE2 OK - CUSTOMER -> CUSTOMER");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAuth.getAction().equals("customer-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário em cliente. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerAccount.sendAndReceive(customer, "account-register");
			System.out.println("STAGE3 OK - CUSTOMER -> ACCOUNT");

//			if (env.getProperty("bantads.rabbit.activate-account-ms") == "true") {
//				if (!transferAccount.getAction().equals("account-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário em conta. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerManager.sendAndReceive(customer, "manager-register");
			System.out.println("STAGE4 OK - CUSTOMER -> MANAGER");

//			if (!transferManager.getAction().equals("manager-ok")) {
//				return new ResponseEntity<>(new JsonResponseObject(false,
//						"Não foi possível atribuir um gerente a conta. Verifique as informações e tente novamente.",
//						null), HttpStatus.INTERNAL_SERVER_ERROR);
//			} else {
//				RabbitCustomerManagerDTO rabbitDTO = new RabbitCustomerManagerDTO();
//				rabbitDTO.setId("254d01fa-4e07-4a81-ab52-e1bbb9fc3a16");
//				rabbitDTO.setIdManager("7e5145ec-f8fd-4190-a01d-cef5e1f3d56d");
//				// Enviar gerente MS CONTA
//				TransferManager transferManagerAccount = producerManagerAccount
//						.sendAndReceive(rabbitDTO, "account-manager");
//				System.out.println("STAGE5 OK");
//
//				if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//					if (!transferManagerAccount.getAction().equals("account-manager-ok")) {
//						return new ResponseEntity<>(new JsonResponseObject(false,
//								"Não foi possível transferir o gerente para a conta. Verifique as informações e tente novamente.",
//								null), HttpStatus.INTERNAL_SERVER_ERROR);
//					}
//				} else {
//
//					
//					// Enviar gerente MS CLIENTE
//					TransferManager transferManagerCustomer = producerManagerCustomer
//							.sendAndReceive(transferManager.getManager(), "customer-manager");
//					System.out.println("STAGE6 OK");
//					if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//
//						if (!transferManagerCustomer.getAction().equals("customer-manager-ok")) {
//							return new ResponseEntity<>(new JsonResponseObject(false,
//									"Não foi possível transferir o gerente para o cliente. Verifique as informações e tente novamente.",
//									null), HttpStatus.INTERNAL_SERVER_ERROR);
//						}
//					}
//				}
//			}

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + e.getMessage() + e.toString() + e.getClass());
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Falha interna do servidor. Verifique as informações e tente novamente.", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new JsonResponseObject(true, "Cliente criado com sucesso", customer),
				HttpStatus.OK);
	}

	@PutMapping
	ResponseEntity<JsonResponseObject> atualizar(@RequestBody CustomerUpdateDTO customerUpdateDTO) {

		if (!SelfRegistrationValidator.validateClienteUpdate(customerUpdateDTO)

		) {
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Dados do cliente inválidos. Verifique as informações e tente novamente", null),
					HttpStatus.BAD_REQUEST);

		}
		if (!SelfRegistrationValidator.validateEndereco(customerUpdateDTO.getEndereco())) {
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Dados do endereço inválidos. Verifique as informações e tente novamente", null),
					HttpStatus.BAD_REQUEST);

		}

		RabbitCustomerUpdateDTO customer;

		try {
			UUID saga = UUID.randomUUID();

			customer = RabbitCustomerMapper.map(customerUpdateDTO, saga, mapper);

			producerAutenticacao.sendAndReceive(customer, "auth-update");
			System.out.println("STAGE1 OK - CUSTOMER -> AUTH");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAuth.getAction().equals("auth-update-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário na autenticação. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerCustomer.sendAndReceive(customer, "customer-update");
			System.out.println("STAGE2 OK - CUSTOMER -> CUSTOMER");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAuth.getAction().equals("customer-update-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário em cliente. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerAccount.sendAndReceive(customer, "account-update");
			System.out.println("STAGE3 OK - CUSTOMER -> ACCOUNT");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAccount.getAction().equals("account-update-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário em conta. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//
//				}
//			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + e.getMessage() + e.toString() + e.getClass());
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Falha interna do servidor. Verifique as informações e tente novamente.", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new JsonResponseObject(true, "Cliente alterado com sucesso", customer),
				HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<JsonResponseObject> delete(@PathVariable("id") String id) {

		CustomerDeleteDTO customer;

		try {
			customer = RabbitCustomerMapper.map(id, mapper);

			producerAutenticacao.sendAndReceive(customer, "auth-delete");
			System.out.println("STAGE1 - AUTH DELETE ENVIADO");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAuth.getAction().equals("auth-delete-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário na autenticação. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerCustomer.sendAndReceive(customer, "customer-delete");
			System.out.println("STAGE2 - CUSTOMER DELETE ENVIADO");

//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAuth.getAction().equals("customer-delete-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário em cliente. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//				}
//			}

			producerAccount.sendAndReceive(customer, "account-delete");
			System.out.println("STAGE3 - ACCOUNT DELETE ENVIADO");
			
			producerManager.sendAndReceive(customer, "manager-delete");
			System.out.println("STAGE4 - MANAGER DELETE ENVIADO");
//			if (env.getProperty("bantads.rabbit.validate-actions") == "true") {
//				if (!transferAccount.getAction().equals("account-delete-ok")) {
//					return new ResponseEntity<>(new JsonResponseObject(false,
//							"Não foi possível criar o usuário em conta. Verifique as informações e tente novamente.",
//							null), HttpStatus.INTERNAL_SERVER_ERROR);
//
//				}
//			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage() + e.getMessage() + e.toString() + e.getClass());
			return new ResponseEntity<>(
					new JsonResponseObject(false,
							"Falha interna do servidor. Verifique as informações e tente novamente.", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(new JsonResponseObject(true, "Cliente delete com sucesso", customer),
				HttpStatus.OK);
	}
}
