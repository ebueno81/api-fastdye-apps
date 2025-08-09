package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.Client;
import com.fastdye.almacen.domain.ports.in.ClientUseCase;
import com.fastdye.almacen.domain.ports.out.ClientRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements ClientUseCase {

    @Autowired
    ClientRepositoryPort clientRepositoryPort;

    @Override
    public Page<Client> findByAll(Pageable pageable) {
        return clientRepositoryPort.findByAll(pageable);
    }

    @Override
    public Optional<Client> findByID(String id) {
        return clientRepositoryPort.findById(id);
    }
}
