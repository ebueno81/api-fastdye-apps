package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.domain.ports.in.ActivityUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityRequest;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityResponseDto;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityRequestMapper;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityUseCase activityUseCase;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ActivityRequest request){
        Activity activity = ActivityRequestMapper.toModel(request);
        Activity saved = activityUseCase.registrar(activity);
        return ResponseEntity.ok("Actividad registrada cod ID: " + saved.getId());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar actividad por ID")
    public ResponseEntity<ActivityResponseDto> findById(@PathVariable int id) {
        return activityUseCase.buscarPorId(id)
                .map(activity -> ResponseEntity.ok(ActivityRestMapper.toResponse(activity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDto>> list() {
        var list = activityUseCase.listar(); // o como lo tengas
        return ResponseEntity.ok(
                list.stream().map(ActivityRestMapper::toResponse).toList()
        );
    }
}
