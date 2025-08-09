package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActivityRequest {
    private String nroSerie;
    private String nroGuia;
    private String observacion;
    private String idCliente;
    private String idAlmacen;
    private String usuarioCreacion;
    private String usuarioModifica;
    private List<ActivityDetailRequest> detalles;
}
