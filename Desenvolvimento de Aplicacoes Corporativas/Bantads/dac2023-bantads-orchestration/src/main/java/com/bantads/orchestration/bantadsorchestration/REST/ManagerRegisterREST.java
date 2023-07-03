package com.bantads.orchestration.bantadsorchestration.REST;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;
import com.bantads.orchestration.bantadsorchestration.mapper.ManagerMapper;
import com.bantads.orchestration.bantadsorchestration.mapper.UserMapper;
import com.bantads.orchestration.bantadsorchestration.model.Manager;

import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerDeleteManagerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Account.ProducerManagerAccount;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Auth.ProducerAuthManager;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Manager.ProducerDeleteManager;
import com.bantads.orchestration.bantadsorchestration.services.Producer.Manager.ProducerManagerCreate;
import com.bantads.orchestration.bantadsorchestration.services.Transfer.Manager.TransferManager;
import com.bantads.orchestration.bantadsorchestration.utils.JsonResponseObject;
import com.bantads.orchestration.bantadsorchestration.validator.ManagerRegisterValidator;

@CrossOrigin
@RestController
@RequestMapping("/managers")
public class ManagerRegisterREST {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProducerManagerCreate producerGerente;


    @Autowired
    private ProducerAuthManager producerAuth;

    @Autowired
    private ProducerManagerAccount producerGerenteConta;

    @Autowired
    private ProducerDeleteManager producerDeleteGerente;

    @Autowired
    private ProducerDeleteManagerAccount producerDeleteGerenteConta;

    @Autowired
    private Environment env;

    @PostMapping
    ResponseEntity<Object> cadastro(@RequestBody ManagerDTO managerDTO) {
        try {
            if (ManagerRegisterValidator.validate(managerDTO)) {
                UUID saga = UUID.randomUUID();

                RabbitManagerDTO manager = ManagerMapper.mapUpdate(managerDTO, saga, mapper);

              producerGerente.sendAndReceive(manager, "create-manager");

            
              producerAuth.sendAndReceive(manager, "create-user");
            //    producerGerenteConta.send(manager);

                return new ResponseEntity<Object>(HttpStatus.CREATED);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")	
    ResponseEntity<Object> deletar(@PathVariable UUID id) {
        try {
//            producerDeleteGerente.send(id);
            producerDeleteGerenteConta.send(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
