package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Activity;
import java.util.List;
import java.util.Optional;

public interface ActivityUseCase {
    Activity registrar(Activity activity);
    Optional<Activity> buscarPorId(int id);
    List<Activity> listar();
}
