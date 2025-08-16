package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityHeaderDto;
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

    public static ActivityHeaderDto toHeaderDto(Activity activity) {
        if (activity == null) return null;

        return ActivityHeaderDto.builder()
                .id(activity.getId())
                .nroSerie(activity.getNroSerie())
                .nroGuia(activity.getNroGuia())
                .observacion(activity.getObservacion())
                .clientId(activity.getClient() != null ? activity.getClient().getId() : null)
                .clientNombre(activity.getClient() != null ? activity.getClient().getNombreCliente() : null)
                .storeId(activity.getStore() != null ? activity.getStore().getId() : null)
                .storeNombre(activity.getStore() != null ? activity.getStore().getNombreAlmacen() : null)
                .idReason(activity.getReason() != null ? activity.getReason().getIdReason() : null)
                .reasonNombre(activity.getReason() != null ? activity.getReason().getNameReason() : null)
                .usuarioCreacion(activity.getUsuarioCreacion())
                .usuarioModifica(activity.getUsuarioModifica())
                .fechaCreacion(activity.getFechaCreacion())
                .build();
    }

}
