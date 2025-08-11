package com.fastdye.almacen.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthResult {
    String token;
    String username;
    String idUser;
}
