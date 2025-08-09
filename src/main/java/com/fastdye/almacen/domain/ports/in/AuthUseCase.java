package com.fastdye.almacen.domain.ports.in;

public interface AuthUseCase {
    String login(String username, String password);
}
