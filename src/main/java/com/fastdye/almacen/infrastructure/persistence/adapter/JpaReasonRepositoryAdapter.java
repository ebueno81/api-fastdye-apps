package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.Reason;
import com.fastdye.almacen.domain.ports.out.ReasonRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.repository.ReasonRepository;
import com.fastdye.almacen.infrastructure.persistence.mapper.ReasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaReasonRepositoryAdapter implements ReasonRepositoryPort {

    @Autowired
    ReasonRepository reasonRepository;

    @Override
    public List<Reason> findAll() {
        return reasonRepository.findByActiveAndTypeReason(0,"ING")
                .stream()
                .map(ReasonMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Reason> findById(String id) {
        return reasonRepository.findById(id)
                .map(ReasonMapper::toModel);
    }
}
