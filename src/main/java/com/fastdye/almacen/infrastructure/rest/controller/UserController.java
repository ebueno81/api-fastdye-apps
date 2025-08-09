package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.ports.in.UserUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.UserResponseDto;
import com.fastdye.almacen.infrastructure.rest.mapper.UserRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserUseCase userUseCase;

    @GetMapping
    @Operation(summary = "Cargar todos los usuarios")
    public ResponseEntity<List<UserResponseDto>> findAll(){
        var users = userUseCase.findByAll();
        return ResponseEntity.ok(UserRestMapper.toResponseList(users));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar por id")
    public ResponseEntity<UserResponseDto> findById(String id){
        return userUseCase.findById(id)
                .map(UserRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
