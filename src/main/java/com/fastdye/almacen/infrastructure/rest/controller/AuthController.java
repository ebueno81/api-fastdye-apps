// infrastructure/rest/controller/AuthController.java
package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.model.AuthResult;
import com.fastdye.almacen.domain.ports.in.AuthUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.LoginRequestDto;
import com.fastdye.almacen.infrastructure.rest.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthUseCase authUseCase;

    @PostMapping("/login")
    @Operation(summary = "Login simulado que devuelve token y nombre de usuario")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        try {
            AuthResult result = authUseCase.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new LoginResponseDto(result.getToken(), result.getUsername(),
                    result.getIdUser()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
