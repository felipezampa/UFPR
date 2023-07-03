package com.bantads.orchestration.bantadsorchestration.mapper;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.bantads.orchestration.bantadsorchestration.DTOs.ManagerDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitManagerDTO;
import com.bantads.orchestration.bantadsorchestration.model.Manager;

public final class ManagerMapper {
    public static Manager map(ManagerDTO managerDTO, UUID saga, ModelMapper mapper) {
        Manager manager = mapper.map(managerDTO, Manager.class);
        manager.setId(UUID.randomUUID());
        manager.setSaga(saga);
        return manager;
    }
    public static RabbitManagerDTO mapUpdate(ManagerDTO managerDTO, UUID saga, ModelMapper mapper) {
        RabbitManagerDTO manager = mapper.map(managerDTO, RabbitManagerDTO.class);
        manager.setId(UUID.randomUUID());
        manager.setSaga(saga);
        return manager;
    }
}
