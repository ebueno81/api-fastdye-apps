package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientUseCase {
    Page<Client> findByAll(Pageable pageable);
    Optional<Client> findByID(String id);
}
