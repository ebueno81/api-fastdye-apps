package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientRepositoryPort {
    Page<Client> findByAll(Pageable pageable);
    Optional<Client> findById(String idCliente);
    Page<Client> search(String q, Pageable pageable);
}
