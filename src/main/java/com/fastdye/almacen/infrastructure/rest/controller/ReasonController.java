package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.ports.in.ReasonUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.ReasonResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fastdye.almacen.infrastructure.rest.mapper.ReasonRestMapper;

import java.util.List;

@RestController
@RequestMapping("api/reason")
public class ReasonController {

    @Autowired
    ReasonUseCase reasonUseCase;

    @GetMapping
    @Operation(summary = "Listado de todos los motivos activos")
    public ResponseEntity<List<ReasonResponseDto>> findAll(){
        var reasons = reasonUseCase.findAll();
        return ResponseEntity.ok(ReasonRestMapper.toResponseList(reasons));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id")
    public ResponseEntity<ReasonResponseDto> findById(String id){
        return reasonUseCase.findById(id)
                .map(ReasonRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
