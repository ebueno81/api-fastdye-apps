package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.Data;

@Data
public class ActivityDetailRequest {
    private int idArticulo;
    private String nroLote;
    private double peso;
    private int cajas;
}
