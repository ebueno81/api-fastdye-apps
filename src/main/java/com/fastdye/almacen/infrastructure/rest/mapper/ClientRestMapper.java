package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.Client;
import com.fastdye.almacen.infrastructure.rest.dto.ClientResponseDto;

public class ClientRestMapper {

    public static ClientResponseDto toResponse(Client client){
        return ClientResponseDto.builder()
                .id(client.getId())
                .nombreCliente(client.getNombreCliente())
                .build();
    }
}
