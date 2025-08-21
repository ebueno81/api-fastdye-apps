package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityDetailEntity;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityEntity;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityHeaderDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ActivityMapper {

    public static ActivityEntity toEntity(Activity model) {
        if (model == null) return null;

        ActivityEntity entity = ActivityEntity.builder()
                .id(model.getId())
                .nroSerie(model.getNroSerie())
                .nroGuia(model.getNroGuia())
                .observacion(model.getObservacion())
                .client(ClientMapper.toEntity(model.getClient()))
                .store(StoreMapper.toEntity(model.getStore()))
                .reason(ReasonMapper.toEntity(model.getReason()))
                .estado(1)
                .activo(1)
                .usuarioCreacion(model.getUsuarioCreacion())
                .usuarioModifica(model.getUsuarioModifica())
                .fechaCreacion(LocalDateTime.now())
                .fechaModifica(LocalDateTime.now())
                .build();

        // Asignar relación inversa a cada detalle
        List<ActivityDetailEntity> detailEntities = model.getDetails().stream()
                .map(d -> {
                    ActivityDetailEntity detalle = ActivityDetailMapper.toEntity(d);
                    detalle.setActivity(entity); // Importante
                    return detalle;
                })
                .collect(Collectors.toList());

        entity.setDetails(detailEntities);

        return entity;
    }

    public static Activity toModel(ActivityEntity entity) {
        if (entity == null) return null;

        return Activity.builder()
                .id(entity.getId())
                .nroSerie(entity.getNroSerie())
                .nroGuia(entity.getNroGuia())
                .observacion(entity.getObservacion())
                .client(ClientMapper.toModel(entity.getClient()))
                .reason(ReasonMapper.toModel(entity.getReason()))
                .store(StoreMapper.toModel(entity.getStore()))
                .fechaCreacion(entity.getFechaCreacion())
                .details(entity.getDetails().stream()
                        .map(ActivityDetailMapper::toModel)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Activity toModelWithoutDetails(ActivityEntity entity) {
        if (entity == null) return null;

        return Activity.builder()
                .id(entity.getId())
                .nroSerie(entity.getNroSerie())
                .nroGuia(entity.getNroGuia())
                .observacion(entity.getObservacion())
                .client(ClientMapper.toModel(entity.getClient()))
                .reason(ReasonMapper.toModel(entity.getReason()))
                .store(StoreMapper.toModel(entity.getStore()))
                .fechaCreacion(entity.getFechaCreacion())
                // No asignamos detalles
                .details(List.of())
                .build();
    }

    public static ActivityHeaderDto toHeaderDto(ActivityEntity entity) {
        if (entity == null) return null;

        double totalPeso = 0.0;
         totalPeso = entity.getDetails().stream()
                .mapToDouble(d -> Objects.isNull(d.getPeso()) ? 0.0 : d.getPeso())
                .sum();

        return ActivityHeaderDto.builder()
                .id(entity.getId())
                .nroSerie(entity.getNroSerie())
                .nroGuia(entity.getNroGuia())
                .observacion(entity.getObservacion())
                .clientId(entity.getClient() != null ? entity.getClient().getIdCliente() : null)
                .clientNombre(entity.getClient() != null ? entity.getClient().getNombreCliente() : null)
                .storeId(entity.getStore() != null ? entity.getStore().getIdAlmacen() : null)
                .storeNombre(entity.getStore() != null ? entity.getStore().getNombreAlmacen() : null)
                .idReason(entity.getReason() != null ? entity.getReason().getIdReason() : null)
                .reasonNombre(entity.getReason() != null ? entity.getReason().getNameReason() : null)
                .usuarioCreacion(entity.getUsuarioCreacion())
                .usuarioModifica(entity.getUsuarioModifica())
                .fechaCreacion(entity.getFechaCreacion())
                .totalPeso(totalPeso)
                .estado(entity.getEstado())
                .build();
    }
}
