package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityDetailResponse;

public class ActivityDetailRestMapper {
    public static ActivityDetailResponse toResponse(ActivityDetail model) {
        return ActivityDetailResponse.builder()
                .id(model.getId())
                .idArticulo(model.getIdArticulo())
                .lote(model.getNroLote())
                .peso(model.getPeso())
                .cajas(model.getCajas())
                .build();
    }
}
