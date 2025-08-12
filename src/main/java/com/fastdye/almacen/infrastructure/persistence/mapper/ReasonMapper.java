package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.Reason;
import com.fastdye.almacen.infrastructure.persistence.entity.ReasonEntity;

public class ReasonMapper {

    public static ReasonEntity toEntity(Reason model){
        if (model == null) return null;

        return ReasonEntity.builder()
                .idReason(model.getIdReason())
                .nameReason(model.getNameReason())
                .typeReason(model.getTypeReason())
                .active(model.getActive())
                .build();
    }

    public static Reason toModel(ReasonEntity entity){
        if (entity == null) return null;

        return Reason.builder()
                .idReason(entity.getIdReason())
                .nameReason(entity.getNameReason())
                .typeReason(entity.getTypeReason())
                .active(entity.getActive())
                .build();
    }

}
