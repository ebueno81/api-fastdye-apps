package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.Client;
import com.fastdye.almacen.infrastructure.persistence.entity.ClientEntity;

public class ClientMapper {
    public static ClientEntity toEntity(Client model) {
        if (model == null) return null;

        return ClientEntity.builder()
                .idCliente(model.getId())
                .nombreCliente(model.getNombreCliente())
                .build();
    }

    public static Client toModel(ClientEntity entity) {
        if (entity == null) return null;

        return Client.builder()
                .id(entity.getIdCliente())
                .nombreCliente(entity.getNombreCliente())
                .build();
    }
}
