package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityHeaderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityRepositoryPort {
    Activity save(Activity activity);
    Optional<Activity> findById(int id);
    List<Activity> findAll();
    Page<ActivityHeaderDto> listarSoloCabecera(String nombreCliente, Pageable pageable);
}
