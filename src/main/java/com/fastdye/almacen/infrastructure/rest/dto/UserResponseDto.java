package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String nombreUsuario;
    private String clave;
}
