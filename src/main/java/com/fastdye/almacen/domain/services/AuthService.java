package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.ports.in.AuthUseCase;
import com.fastdye.almacen.domain.ports.in.UserUseCase;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class AuthService implements AuthUseCase {

    private final UserUseCase userUseCase;

    @Override
    public String login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Usuario o contraseña inválidos");
        }

        // ✅ Validación opcional contra tu caso de uso real
        var existeUsuario = userUseCase.findById(username).isPresent();
        if (!existeUsuario) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        // Simular un token como texto (UUID)
        return "FAKE-TOKEN-" + UUID.randomUUID();
    }
}
