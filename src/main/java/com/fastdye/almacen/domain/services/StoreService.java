package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.Store;
import com.fastdye.almacen.domain.ports.in.StoreUseCase;
import com.fastdye.almacen.domain.ports.out.StoreRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements StoreUseCase {

    @Autowired
    StoreRepositoryPort storeRepositoryPort;

    @Override
    public List<Store> findAll() {
        return storeRepositoryPort.findAll();
    }

    @Override
    public Optional<Store> findById(String idAlmacen) {
        return storeRepositoryPort.findById(idAlmacen);
    }
}
