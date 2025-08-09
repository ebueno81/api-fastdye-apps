package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}
