package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityDetailRequest;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityDetailResponse;


public class ActivityDetailRestMapper {

    public static ActivityDetailResponse toResponse(ActivityDetail model) {
        return ActivityDetailResponse.builder()
                .id(model.getId())
                .idArticulo(model.getIdArticulo())
                .lote(model.getNroLote())
                .peso(model.getPeso())
                .cajas(model.getCajas())
                .nombreArticulo(model.getNombreArticulo())
                .build();
    }

    public static ActivityDetail toDomain(ActivityDetailRequest request) {
        ActivityDetail detail = new ActivityDetail();
        detail.setIdArticulo(request.getIdArticulo());
        detail.setNroLote(request.getNroLote());
        detail.setPeso(request.getPeso());
        detail.setCajas(request.getCajas());
        return detail;
    }

}
