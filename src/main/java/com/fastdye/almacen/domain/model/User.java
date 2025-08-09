package com.fastdye.almacen.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    String id;
    String nombreUsuario;
    String clave;
    int activo;
}
