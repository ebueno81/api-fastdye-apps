package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.domain.ports.in.ActivityUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityRequest;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityResponseDto;
import com.fastdye.almacen.infrastructure.rest.dto.UpdateActivityHeaderRequest;
import com.fastdye.almacen.infrastructure.rest.dto.UpsertActivityDetailsRequest;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityRequestMapper;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityRestMapper;
import com.fastdye.almacen.infrastructure.rest.mapper.UpsertDetailsRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityUseCase activityUseCase;

    @PostMapping
    public ResponseEntity<ActivityResponseDto> save(@RequestBody ActivityRequest request){
        Activity activity = ActivityRequestMapper.toModel(request);
        Activity saved = activityUseCase.registrar(activity);
        ActivityResponseDto body = ActivityRestMapper.toResponse(saved);
        return ResponseEntity
                .created(URI.create("/api/activity/" + saved.getId()))
                .body(body);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cabecera de actividad")
    public ResponseEntity<ActivityResponseDto> updateHeader(
            @PathVariable int id,
            @RequestBody UpdateActivityHeaderRequest req) {

        var cmd = new ActivityUseCase.UpdateActivityHeaderCommand(
                req.getNroSerie(),
                req.getNroGuia(),
                req.getObservacion(),
                req.getIdCliente(),
                req.getIdAlmacen(),
                req.getIdReason(),
                req.getUsuarioModifica()
        );
        Activity updated = activityUseCase.actualizarCabecera(id, cmd);
        return ResponseEntity.ok(ActivityRestMapper.toResponse(updated));
    }

    @PutMapping("/{id}/details")
    public ResponseEntity<ActivityResponseDto> upsertDetails(
            @PathVariable int id,
            @RequestBody UpsertActivityDetailsRequest req) {

        var cmd = UpsertDetailsRestMapper.toCommand(req);
        Activity updated = activityUseCase.upsertDetalles(id, cmd);
        return ResponseEntity.ok(ActivityRestMapper.toResponse(updated));
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
