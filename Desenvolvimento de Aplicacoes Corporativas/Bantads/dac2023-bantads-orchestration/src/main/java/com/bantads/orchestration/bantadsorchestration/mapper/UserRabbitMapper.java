package com.bantads.orchestration.bantadsorchestration.mapper;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.bantads.orchestration.bantadsorchestration.DTOs.UserDTO;
import com.bantads.orchestration.bantadsorchestration.DTOs.RabbitUserDTO;

public final class UserRabbitMapper {
    public static RabbitUserDTO map(UserDTO usuarioDTO, UUID saga, ModelMapper mapper) {
        RabbitUserDTO usuario = mapper.map(usuarioDTO, RabbitUserDTO.class);
        usuario.setId(UUID.randomUUID());
        usuario.setSaga(saga);
        return usuario;
    }
}
