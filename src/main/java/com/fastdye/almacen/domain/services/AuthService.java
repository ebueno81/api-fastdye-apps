package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.AuthResult;
import com.fastdye.almacen.domain.ports.in.AuthUseCase;
import com.fastdye.almacen.domain.ports.in.UserUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserUseCase userUseCase;

    @Override
    public AuthResult login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Usuario o contraseña inválidos");
        }

        // OJO: si tu findById busca por "id" y no por "username",
        // cambia esta línea por una findByUsername(...) o ajusta el repositorio.
        var user = userUseCase.findById(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        String token = "FAKE-TOKEN-" + UUID.randomUUID();
        String displayName = (user.getNombreUsuario() != null && !user.getNombreUsuario().isBlank())
                ? user.getNombreUsuario()
                : username;

        return AuthResult.builder()
                .token(token)
                .username(displayName)
                .idUser(user.getId())
                .build();
    }
}
