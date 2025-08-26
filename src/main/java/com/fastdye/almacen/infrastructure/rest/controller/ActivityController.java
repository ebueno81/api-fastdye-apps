package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.domain.ports.in.ActivityDetailUseCase;
import com.fastdye.almacen.domain.ports.in.ActivityUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.*;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityDetailRestMapper;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityRequestMapper;
import com.fastdye.almacen.infrastructure.rest.mapper.ActivityRestMapper;
import com.fastdye.almacen.infrastructure.rest.mapper.UpsertDetailsRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityUseCase activityUseCase;
    @Autowired
    ActivityDetailUseCase activityDetailUseCase;

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

    @GetMapping("/{id:\\d+}")
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

    // Crear detalle
    @PostMapping("/{id}/details")
    @Operation(summary = "Crear detalle por separado")
    public ResponseEntity<ActivityDetail> crearDetalle(
            @PathVariable int id,
            @RequestBody ActivityDetailRequest request) {
        ActivityDetail detail = ActivityDetailRestMapper.toDomain(request);
        return ResponseEntity.ok(activityDetailUseCase.crearDetalle(detail));
    }

    // Actualizar detalle
    @PutMapping("/details/{detailId}")
    @Operation(summary = "Actualizar detalle")
    public ResponseEntity<ActivityDetail> actualizarDetalle(
            @PathVariable int detailId,
            @RequestBody ActivityDetailRequest request) {
        ActivityDetail detail = ActivityDetailRestMapper.toDomain(request);
        return ResponseEntity.ok(activityDetailUseCase.actualizarDetalle(detailId, detail));
    }

    // Anular detalle
    @PatchMapping("/details/{detailId}/anular")
    @Operation(summary = "anular detalle por id")
    public ResponseEntity<Void> anularDetalle(@PathVariable int detailId) {
        activityDetailUseCase.anularDetalle(detailId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/headers")
    public Page<ActivityHeaderDto> listarHeaders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String nombreCliente
    ) {
        Pageable pageable = PageRequest.of(page, size); // 👉 sin ordenamiento
        return activityUseCase.listarSoloCabecera(nombreCliente, pageable);
    }


}
