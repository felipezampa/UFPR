package com.bantads.orchestration.bantadsorchestration.mapper;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import com.bantads.orchestration.bantadsorchestration.DTOs.UserDTO;
import com.bantads.orchestration.bantadsorchestration.model.User;

public final class UserMapper {
    public static User map(UserDTO usuarioDTO, UUID saga, ModelMapper mapper) {
        User usuario = mapper.map(usuarioDTO, User.class);
        usuario.setId(UUID.randomUUID());
        usuario.setSaga(saga);
        return usuario;
    }
}
