package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.AuthResult;

public interface AuthUseCase {
    AuthResult login(String username, String password);
}
