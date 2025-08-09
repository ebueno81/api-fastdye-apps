package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepositoryPort {
    List<Store> findAll();
    Optional<Store> findById(String idAlmacen);
}
