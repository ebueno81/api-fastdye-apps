package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.Reason;
import com.fastdye.almacen.infrastructure.rest.dto.ReasonResponseDto;

import java.util.List;

public class ReasonRestMapper {

    public static ReasonResponseDto toResponse(Reason reason){
        return ReasonResponseDto.builder()
                .idReason(reason.getIdReason())
                .nameReason(reason.getNameReason())
                .typeReason(reason.getTypeReason())
                .build();
    }

    public static List<ReasonResponseDto> toResponseList(List<Reason> reasons){
        return reasons.stream().map(ReasonRestMapper::toResponse).toList();
    }
}
