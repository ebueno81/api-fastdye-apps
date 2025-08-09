package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.ports.in.StoreUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.StoreResponseDto;
import com.fastdye.almacen.infrastructure.rest.mapper.StoreRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    StoreUseCase storeUseCase;

    @GetMapping
    @Operation(summary = "Listar almacenes")
    public ResponseEntity<List<StoreResponseDto>> findAll() {
        var stores = storeUseCase.findAll();
        return ResponseEntity.ok(StoreRestMapper.toResponseList(stores));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar almacén por ID")
    public ResponseEntity<StoreResponseDto> findById(@PathVariable String id) {
        return storeUseCase.findById(id)
                .map(StoreRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
