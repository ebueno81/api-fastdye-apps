package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ActivityHeaderDto {
    private int id;
    private String nroSerie;
    private String nroGuia;
    private String observacion;

    private String clientId;
    private String clientNombre;

    private String storeId;
    private String storeNombre;

    private String idReason;
    private String reasonNombre;

    private String usuarioCreacion;
    private String usuarioModifica;
    private LocalDateTime fechaCreacion;

    private double totalPeso;
}
