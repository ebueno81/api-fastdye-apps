package com.fastdye.almacen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDetail {
    private int id;
    private int idActividad;
    private Integer idArticulo;
    private String nroLote;
    private double peso;
    private int cajas;
    private int activo;
    private String nombreArticulo;
}
