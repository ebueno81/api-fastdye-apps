package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.Store;
import com.fastdye.almacen.infrastructure.persistence.entity.StoreEntity;

public class StoreMapper {
    public static StoreEntity toEntity(Store model) {
        if (model == null) return null;

        return StoreEntity.builder()
                .idAlmacen(model.getId())
                .nombreAlmacen(model.getNombreAlmacen())
                .build();
    }

    public static Store toModel(StoreEntity entity) {
        if (entity == null) return null;

        return Store.builder()
                .id(entity.getIdAlmacen())
                .nombreAlmacen(entity.getNombreAlmacen())
                .build();
    }
}
