package com.fastdye.almacen.application.service;

import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.domain.ports.in.ActivityDetailUseCase;
import com.fastdye.almacen.domain.ports.out.ActivityDetailRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ActivityDetailService implements ActivityDetailUseCase {

    private final ActivityDetailRepositoryPort repository;

    public ActivityDetailService(ActivityDetailRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public ActivityDetail crearDetalle(ActivityDetail detail) {
        detail.setActivo(1);
        return repository.save(detail);
    }

    @Override
    public ActivityDetail actualizarDetalle(int id, ActivityDetail updatedDetail) {
        ActivityDetail existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));

        // Mantener la referencia al idActividad
        updatedDetail.setIdActividad(existing.getIdActividad());

        // Actualizar campos editables
        existing.setIdArticulo(updatedDetail.getIdArticulo());
        existing.setNroLote(updatedDetail.getNroLote());
        existing.setPeso(updatedDetail.getPeso());
        existing.setCajas(updatedDetail.getCajas());

        return repository.save(existing);
    }



    @Override
    public void anularDetalle(int idDetalle) {
        ActivityDetail existing = repository.findById(idDetalle)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        existing.setActivo(0);
        repository.save(existing);
    }
}
