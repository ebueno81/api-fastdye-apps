package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.Store;
import com.fastdye.almacen.domain.ports.out.StoreRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.mapper.StoreMapper;
import com.fastdye.almacen.infrastructure.persistence.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaStoreRepositoryAdapter implements StoreRepositoryPort {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Store> findById(String idAlmacen) {
        return storeRepository.findById(idAlmacen)
                .map(StoreMapper::toModel);
    }
}
