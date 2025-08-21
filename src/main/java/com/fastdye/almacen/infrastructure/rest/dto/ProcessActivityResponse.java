package com.fastdye.almacen.infrastructure.rest.dto;

public record ProcessActivityResponse(
        int activityId,
        int idIngresoCreado,
        String status
) {}
