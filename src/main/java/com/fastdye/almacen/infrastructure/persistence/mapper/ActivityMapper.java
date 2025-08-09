package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityDetailEntity;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityEntity;

import java.time.LocalDateTime;
import java.util.List;
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
                .store(StoreMapper.toModel(entity.getStore()))
                .details(entity.getDetails().stream()
                        .map(ActivityDetailMapper::toModel)
                        .collect(Collectors.toList()))
                .build();
    }
}
