package com.bantads.orchestration.bantadsorchestration.mapper;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerUpdateDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.CustomerDeleteDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitCustomerUpdateDTO;

public final class RabbitCustomerMapper {
    public static RabbitCustomerDTO map(CustomerDTO customerDTO, UUID saga, ModelMapper mapper) {
        RabbitCustomerDTO rabbitCustomer = mapper.map(customerDTO, RabbitCustomerDTO.class);
        rabbitCustomer.setId(UUID.randomUUID());
        rabbitCustomer.setSaga(saga);
        return rabbitCustomer;
    }
    
    public static RabbitCustomerUpdateDTO map(CustomerUpdateDTO customerDTO, UUID saga, ModelMapper mapper) {
        RabbitCustomerUpdateDTO rabbitCustomer = mapper.map(customerDTO, RabbitCustomerUpdateDTO.class);
        rabbitCustomer.setSaga(saga);
        return rabbitCustomer;
    }
    
    public static CustomerDeleteDTO map(String customerId, ModelMapper mapper) {
        CustomerDeleteDTO customerDTO = new CustomerDeleteDTO();
        customerDTO.setId(customerId);
        return customerDTO;
    }
}
