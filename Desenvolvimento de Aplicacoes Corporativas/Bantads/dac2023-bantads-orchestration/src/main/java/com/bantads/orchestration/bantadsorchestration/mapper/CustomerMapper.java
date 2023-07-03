package com.bantads.orchestration.bantadsorchestration.mapper;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDTO;
import com.bantads.orchestration.bantadsorchestration.model.Customer;

public final class CustomerMapper {
    public static Customer map(CustomerDTO customerDTO, UUID saga, UUID usuarioId, ModelMapper mapper) {
        Customer customer = mapper.map(customerDTO, Customer.class);
        customer.setId(UUID.randomUUID());
        customer.setSaga(saga);
        customer.setIdExternoUsuario(usuarioId);
        return customer;
    }
}
