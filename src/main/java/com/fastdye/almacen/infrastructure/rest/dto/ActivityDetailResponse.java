package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDetailResponse {
    private int id;
    private int idActividad;
    private int idArticulo;
    private String lote;
    private double peso;
    private int cajas;
    private String nombreArticulo;
}
