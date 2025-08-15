package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.domain.ports.out.ActivityDetailRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityDetailEntity;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityEntity;
import com.fastdye.almacen.infrastructure.persistence.mapper.ActivityDetailMapper;
import com.fastdye.almacen.infrastructure.persistence.repository.ActivityDetailRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaActivityDetailRepositoryAdapter implements ActivityDetailRepositoryPort {

    private final ActivityDetailRepository repository;

    public JpaActivityDetailRepositoryAdapter(ActivityDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public ActivityDetail save(ActivityDetail detail) {
        ActivityDetailEntity entity = ActivityDetailMapper.toEntity(detail);

        if (detail.getIdActividad() > 0) {
            ActivityEntity activity = new ActivityEntity();
            activity.setId(detail.getIdActividad());
            entity.setActivity(activity);
        }

        return ActivityDetailMapper.toModel(repository.save(entity));
    }

    @Override
    public Optional<ActivityDetail> findById(int id) {
        return repository.findById(id).map(ActivityDetailMapper::toModel);
    }
}
