package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.Client;
import com.fastdye.almacen.domain.ports.out.ClientRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.mapper.ClientMapper;
import com.fastdye.almacen.infrastructure.persistence.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaClientRepositoryAdapter implements ClientRepositoryPort {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Page<Client> findByAll(Pageable pageable) {
        return (Page<Client>) clientRepository.findAll(pageable)
                .map(ClientMapper::toModel);
    }

    @Override
    public Optional<Client> findById(String idCliente) {
        return clientRepository.findById(idCliente)
                .map(ClientMapper::toModel);
    }
}
