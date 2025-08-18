package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.model.Client;
import com.fastdye.almacen.domain.ports.in.ClientUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.ClientResponseDto;
import com.fastdye.almacen.infrastructure.rest.mapper.ClientRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/client")
public class ClientController {

    @Autowired
    ClientUseCase clientUseCase;

    @GetMapping
    @Operation(summary = "Listado de todos los clientes (con filtro opcional)")
    public ResponseEntity<Page<ClientResponseDto>> findAll(
            @RequestParam(value = "q", required = false) String q,
            @ParameterObject
            @PageableDefault(size = 20, sort = "nombreCliente") Pageable pageable) {
        Page<Client> clients = (q == null || q.isBlank())
                ? clientUseCase.findByAll(pageable)
                : clientUseCase.search(q.trim(), pageable);

        return ResponseEntity.ok(clients.map(ClientRestMapper::toResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por Id")
    public ResponseEntity<ClientResponseDto> findById(String id){
        return clientUseCase.findByID(id)
                .map(ClientRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
