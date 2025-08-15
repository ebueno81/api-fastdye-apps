package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.ActivityDetail;

public interface ActivityDetailUseCase {
    ActivityDetail crearDetalle(ActivityDetail activityDetail);
    ActivityDetail actualizarDetalle(int id, ActivityDetail activityDetail);
    void anularDetalle(int idDetalle);
}
