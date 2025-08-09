package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.ports.in.AuthUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.LoginRequestDto;
import com.fastdye.almacen.infrastructure.rest.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthUseCase authUseCase;

    @PostMapping("/login")
    @Operation(summary = "Login simulado que devuelve un token de prueba")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            String token = authUseCase.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new LoginResponseDto(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
