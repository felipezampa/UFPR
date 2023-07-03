package br.ufpr.bantads.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.ufpr.bantads.model.dto.ClienteDTO;
import br.ufpr.bantads.model.entity.Cliente;
import br.ufpr.bantads.repository.ClienteRepository;
import br.ufpr.bantads.service.GerenteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufpr.bantads.model.entity.Gerente;
import br.ufpr.bantads.model.dto.GerenteDTO;
import br.ufpr.bantads.repository.GerenteRepository;
import br.ufpr.bantads.response.GerenteResponse;

@CrossOrigin
@RestController
public class GerenteController {

    @Autowired
    private GerenteService service;
    @Autowired
    private GerenteRepository gerenteRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/gerentes")
    public ResponseEntity<Object> inserir(@RequestBody GerenteDTO gerente) throws JsonProcessingException {
        Gerente g = gerenteRepo.findByCpf(gerente.getCpf());
        if (g == null) {
            // Salva a entidade convertida do DTO no bd
            Gerente novoGerente = modelMapper.map(gerente, Gerente.class);

            List<Gerente> listaGerentes = gerenteRepo.findAll();
            // Busca o gerente com mais clientes
            Gerente gerenteComMaisClientes = gerenteRepo.findFirstByOrderByNumClientesDesc();
            // Busca o primeiro cliente que retornar do gerente com mais clientes
            Cliente cliente = clienteRepo.findFirstByGerenteId(gerenteComMaisClientes.getId());
            List<Cliente> listClientes = clienteRepo.findAll();

            if (listaGerentes.size() > 1 && listClientes.size() > 1) {
                // Altera o número de clientes do gerente para 1 caso tenha sido inserido qualquer valor no POST
                novoGerente.setNumClientes(1);
                gerenteRepo.save(novoGerente);
                // Altera o gerente do cliente selecionado para o gerente sendo inserido no banco
                cliente.setGerente(novoGerente);
                clienteRepo.save(cliente);
                // Diminui em 1 o número de contas do cliente que tem mais contas
                gerenteComMaisClientes.setNumClientes(gerenteComMaisClientes.getNumClientes() - 1);
                gerenteRepo.save(gerenteComMaisClientes);
            } else {
                // R17 Se for o primeiro gerente a ser cadastrado, ou se só houver mais um gerente com uma conta atrelado, este gerente fica sem nenhuma conta;
                novoGerente.setNumClientes(0);
                gerenteRepo.save(novoGerente);
            }

            // Converte gernte em uma string JSON
            String jsonGerente = objectMapper.writeValueAsString(novoGerente);
            // Converte string JSON para um node e envia para o service
            JsonNode node = objectMapper.readTree(jsonGerente);
            service.enviarGerente(node, "create");

            return new ResponseEntity<>(new GerenteResponse(true, "Gerente criado com sucesso", modelMapper.map(novoGerente, GerenteDTO.class)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new GerenteResponse(false, "Já possui um gerente com este CPF", null), HttpStatus.CONFLICT);
    }

    @GetMapping("/gerentes")
    public List<GerenteDTO> listarTodos() {
        List<Gerente> lista = gerenteRepo.findAll();
        return lista.stream().map(e -> modelMapper.map(e, GerenteDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/gerentes/{id}")
    public ResponseEntity<Object> obterPorId(@PathVariable("id") Long id) {
        Optional<Gerente> g = gerenteRepo.findById(id);

        if (g.isEmpty()) {
            return new ResponseEntity<>(new GerenteResponse(false, "Gerente informado não encontrado", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new GerenteResponse(true, "Gerente encontrado com sucesso", modelMapper.map(g, GerenteDTO.class)), HttpStatus.OK);
    }

    @PutMapping("/gerentes/{id}")
    public ResponseEntity<Object> alterarGerente(@PathVariable("id") Long id, @RequestBody GerenteDTO gerente) throws JsonProcessingException {
        Optional<Gerente> g = gerenteRepo.findById(id);
        if (g.isPresent()) {
            Gerente ger = g.get();
            ger.setNome(gerente.getNome());
            // Nao pode alterar o cpf
            ger.setEmail(gerente.getEmail());
            ger.setPhone(gerente.getPhone());
            // Tambem nao faz sentido alterar o numero de clientes
            gerenteRepo.save(ger);

            // Converte gernte em uma string JSON
            String jsonGerente = objectMapper.writeValueAsString(ger);
            // Converte string JSON para um node e envia para o service
            JsonNode node = objectMapper.readTree(jsonGerente);
            service.enviarGerente(node, "update");

            return new ResponseEntity<>(new GerenteResponse(true, "Gerente alterado com sucesso", modelMapper.map(ger, GerenteDTO.class)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new GerenteResponse(false, "Gerente não encontrado", null), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/gerentes/{id}")
    public ResponseEntity<Object> excluirGerente(@PathVariable("id") Long id) throws JsonProcessingException {
        Optional<Gerente> g = gerenteRepo.findById(id);
        if (g.isPresent()) {
            List<Gerente> gerentes = gerenteRepo.findAll();
            // Requisito 18: Nao permitir a remocao do ultimo gerente do banco.
            if (gerentes.size() > 1) {
                // Recupera o gerente
                Gerente ger = g.get();
                // Recupera todos os clientes do gerente a ser excluido
                List<Cliente> clientes = clienteRepo.findByGerenteId(ger.getId());
                // Recupera o gerente com menos clientes;
                Gerente newGerente = gerenteRepo.findFirstByOrderByNumClientes();
                // Loop por todos os clientes atribuindo o novo gerente para cada um
                for (Cliente cli : clientes) {
                    cli.setGerente(newGerente);
                    newGerente.setNumClientes(newGerente.getNumClientes() + 1);
                    clienteRepo.save(cli);
                }

                // Converte gerente em uma string JSON
                String jsonGerente = objectMapper.writeValueAsString(ger);
                // Converte string JSON para um node e envia para o service
                JsonNode node = objectMapper.readTree(jsonGerente);
                // Envia para o Rabbit que excluiu
                service.enviarGerente(node, "delete");
                // Exclui o gerente local
                gerenteRepo.delete(ger);
                return new ResponseEntity<>(new GerenteResponse(true, "Gerente excluído com sucesso", modelMapper.map(g, GerenteDTO.class)), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(new GerenteResponse(false, "Gerente não encontrado", null), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/clientes-gerente")
    public List<ClienteDTO> listarTodosClientes(@RequestParam(value = "aprovado", required = false) Boolean aprovado) {
        List<Cliente> lista;

        if (aprovado != null) {
            lista = clienteRepo.findByAprovado(aprovado);
        } else {
            lista = clienteRepo.findAll();
        }

        return lista.stream().map(e -> modelMapper.map(e, ClienteDTO.class)).collect(Collectors.toList());
    }

    @PutMapping("/clientes-gerente/{id}")
    public ResponseEntity<Object> aprovarCliente(@PathVariable("id") Long id) throws JsonProcessingException {
        Optional<Cliente> c = clienteRepo.findById(id);
        if (c.isPresent()) {
            Cliente cli = c.get();
            cli.setAprovado(true);
            clienteRepo.save(cli);

            if (cli.getAprovado() == true){
                service.aprovarCliente(cli);
            }

            return new ResponseEntity<>(new GerenteResponse(true, "Cliente alterado com sucesso", modelMapper.map(cli, ClienteDTO.class)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new GerenteResponse(false, "Cliente não encontrado", null), HttpStatus.NOT_FOUND);
    }
}
