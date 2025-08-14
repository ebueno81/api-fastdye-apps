package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.Data;

@Data
public class UpdateActivityHeaderRequest {
    private String nroSerie;
    private String nroGuia;
    private String observacion;
    private String idCliente;
    private String idAlmacen;
    private String idReason;
    private String usuarioModifica;
}
