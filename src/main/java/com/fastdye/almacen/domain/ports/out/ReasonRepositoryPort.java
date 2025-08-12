package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Reason;

import java.util.List;
import java.util.Optional;

public interface ReasonRepositoryPort {
    List<Reason> findAll();
    Optional<Reason> findById(String id);
}