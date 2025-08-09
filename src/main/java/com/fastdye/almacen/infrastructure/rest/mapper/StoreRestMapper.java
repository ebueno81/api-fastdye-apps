package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.Store;
import com.fastdye.almacen.infrastructure.rest.dto.StoreResponseDto;

import java.util.List;

public class StoreRestMapper {
    public static StoreResponseDto toResponse(Store store) {
        return StoreResponseDto.builder()
                .id(store.getId())
                .nombreAlmacen(store.getNombreAlmacen())
                .build();
    }

    public static List<StoreResponseDto> toResponseList(List<Store> stores) {
        return stores.stream().map(StoreRestMapper::toResponse).toList();
    }
}
