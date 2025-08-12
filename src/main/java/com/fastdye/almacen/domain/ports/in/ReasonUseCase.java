package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Reason;

import java.util.List;
import java.util.Optional;

public interface ReasonUseCase {
    List<Reason> findAll();
    Optional<Reason> findById(String id);
}
