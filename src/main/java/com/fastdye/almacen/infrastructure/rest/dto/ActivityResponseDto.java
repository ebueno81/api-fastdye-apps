package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityResponseDto {
    private int id;
    private String nroSerie;
    private String nroGuia;
    private String observacion;
    private String clientId;
    private String clientNombre;
    private String storeId;
    private String storeNombre;
    private String userId;
    private String userNombre;
    private String idReason;
    private String reasonNombre;
    private LocalDateTime fechaCreacion;
    private List<ActivityDetailResponse> detalles;
}
