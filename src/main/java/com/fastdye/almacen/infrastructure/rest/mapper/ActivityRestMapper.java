package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityResponseDto;

public class ActivityRestMapper {
    public static ActivityResponseDto toResponse(Activity model) {
        return ActivityResponseDto.builder()
                .id(model.getId())
                .nroSerie(model.getNroSerie())
                .nroGuia(model.getNroGuia())
                .observacion(model.getObservacion())
                .clientId(model.getClient().getId())
                .clientNombre(model.getClient().getNombreCliente())
                .storeId(model.getStore().getId())
                .storeNombre(model.getStore().getNombreAlmacen())
                .reasonNombre(model.getReason().getNameReason())
                .userId(model.getUsuarioCreacion())
                .fechaCreacion(model.getFechaCreacion())
                .detalles(model.getDetails().stream()
                        .map(ActivityDetailRestMapper::toResponse)
                        .toList())
                .build();
    }


}
