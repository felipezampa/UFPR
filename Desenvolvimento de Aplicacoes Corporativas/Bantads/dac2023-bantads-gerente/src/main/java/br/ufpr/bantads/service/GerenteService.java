package br.ufpr.bantads.service;

import br.ufpr.bantads.model.dto.*;
import br.ufpr.bantads.model.entity.Cliente;
import br.ufpr.bantads.model.entity.Gerente;
import br.ufpr.bantads.rabbitmq.GerenteProducer;
import br.ufpr.bantads.repository.ClienteRepository;
import br.ufpr.bantads.repository.GerenteRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepo;
    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GerenteProducer gerenteProducer;

    public void criarGerenteCliente(JsonNode node) {
        // Obtem dados do Json
        JsonNode managerNode = node.get("manager");
        Long id = managerNode.get("id").asLong();
        String cpf = managerNode.get("cpf").asText();
        String nome = managerNode.get("nome").asText();
        Double salario = managerNode.get("salario").asDouble();
        String saga = managerNode.get("id").asText();
        Gerente g = gerenteRepo.findFirstByOrderByNumClientes();

        // Cria um cliente e salva no banco
        Cliente cl = new Cliente(id, nome, cpf, salario, saga,false, g );
        clienteRepo.save(cl);
        // incremente o num de clientes do gerente
        g.setNumClientes(g.getNumClientes() + 1);
        gerenteRepo.save(g);

    }

    public void updateGerenteCliente(JsonNode node) {
        // Obtem dados do Json
        JsonNode managerNode = node.get("manager");
        String cpf = managerNode.get("cpf").asText();
        String nome = managerNode.get("nome").asText();
        Double salario = managerNode.get("salario").asDouble();
        String saga = managerNode.get("id").asText();

        // Cria um cliente e salva no banco
        Cliente cliente = clienteRepo.findByCpf(cpf);
        cliente.setNome(nome);
        cliente.setSalario(salario);
        cliente.setSaga(saga);
        clienteRepo.save(cliente);
        // Nao precisa modificar o gerente, ele nao tem nada a ver com o cliente sendo modificado

        // Prepara o objeto para enviar a response
        Gerente g = cliente.getGerente();
        String idResponse = managerNode.get("id").asText();
        GerenteResponseDTO gerenteDTO = new GerenteResponseDTO(idResponse, g.getId().toString());
        ResponseDTO responseDTO = new ResponseDTO(gerenteDTO, "manager-customer-updated");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.valueToTree(responseDTO);
        gerenteProducer.sendMessage(node, "manager-customer-updated", "RESPONSE GERENTE" + responseNode);
    }

    @Transactional
    public void removerGerenteCliente(JsonNode node) {
        // Obtem dados do Json
        JsonNode managerNode = node.get("manager");
        String id = managerNode.get("id").asText();
        // Recupera o cliente a ser excluido do banco
        Cliente cliente = clienteRepo.findBySaga(id);
        // Recupera o gerente do cliente a ser excluido
        Gerente g = cliente.getGerente();
        // Exclui o cliente do banco
        clienteRepo.delete(cliente);
        // Atualiza o numero de clientes do gerente
        g.setNumClientes(g.getNumClientes() - 1);
        // Salva o gerente no banco
        gerenteRepo.save(g);
        // RespondeDTO vazia para informar que foi excluido com sucesso
        String idResponse = managerNode.get("id").asText();
        GerenteResponseDTO gerenteDTO = new GerenteResponseDTO(idResponse, g.getId().toString());
        ResponseDTO responseDTO = new ResponseDTO(gerenteDTO, "manager-customer-deleted");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.valueToTree(responseDTO);
        gerenteProducer.sendMessage(node, "manager-customer-deleted", "RESPONSE GERENTE" + responseNode);
    }

    public void enviarGerente(JsonNode node, String action) {

        String id = node.get("id").asText();
        String nome = node.get("nome").asText();
        String email = node.get("id").asText();
        GerenteAuthDTO gerenteAuthDTO = new GerenteAuthDTO(id, nome, email);
        ObjectMapper objectMapper = new ObjectMapper();

        if (action.equals("create")) {
            GerenteAuthResponseDTO gerenteAuthResponseDTO = new GerenteAuthResponseDTO(gerenteAuthDTO, "gerente-auth-create");
            JsonNode responseNode = objectMapper.valueToTree(gerenteAuthResponseDTO);
            gerenteProducer.sendMessage(node, "gerente-auth-create", "RESPONSE GERENTE CRIADO ->" + responseNode);
        } else if (action.equals("update")) {
            GerenteAuthResponseDTO gerenteAuthResponseDTO = new GerenteAuthResponseDTO(gerenteAuthDTO, "gerente-auth-update");
            JsonNode responseNode = objectMapper.valueToTree(gerenteAuthResponseDTO);
            gerenteProducer.sendMessage(node, "gerente-auth-update", "RESPONSE GERENTE ATUALIZADO ->" + responseNode);
        } else {
            GerenteAuthResponseDTO gerenteAuthResponseDTO = new GerenteAuthResponseDTO(gerenteAuthDTO, "gerente-auth-delete");
            JsonNode responseNode = objectMapper.valueToTree(gerenteAuthResponseDTO);
            gerenteProducer.sendMessage(node, "gerente-auth-delete", "RESPONSE GERENTE EXCLUIDO ->" + responseNode);
        }

    }

    public void aprovarCliente(Cliente cliente) {
        // Prepara o objeto para enviar a response
        Gerente g = cliente.getGerente();
        String idResponse = cliente.getSaga();
        GerenteResponseDTO gerenteDTO = new GerenteResponseDTO(idResponse, g.getId().toString());
        ResponseDTO responseDTO = new ResponseDTO(gerenteDTO, "manager-customer-ok");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.valueToTree(responseDTO);
        gerenteProducer.sendMessage(responseNode, "manager-customer-ok", "RESPONSE GERENTE CLIENTE APROVADO ->" + responseNode);
    }

}
