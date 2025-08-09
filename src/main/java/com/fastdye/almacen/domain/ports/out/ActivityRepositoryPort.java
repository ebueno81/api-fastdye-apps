package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityRepositoryPort {
    Activity save(Activity activity);
    Optional<Activity> findById(int id);
    List<Activity> findAll();
}
