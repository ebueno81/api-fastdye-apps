package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.Reason;
import com.fastdye.almacen.domain.ports.in.ReasonUseCase;
import com.fastdye.almacen.domain.ports.out.ReasonRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReasonService implements ReasonUseCase {

    @Autowired
    ReasonRepositoryPort reasonRepositoryPort;

    @Override
    public List<Reason> findAll() {
        return reasonRepositoryPort.findAll();
    }

    @Override
    public Optional<Reason> findById(String id) {
        return reasonRepositoryPort.findById(id);
    }
}
