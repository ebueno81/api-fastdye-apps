package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Store;

import java.util.List;
import java.util.Optional;

public interface StoreUseCase {
    List<Store> findAll();
    Optional<Store> findById(String idAlmacen);
}
