package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.domain.ports.out.ActivityRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityEntity;
import com.fastdye.almacen.infrastructure.persistence.mapper.ActivityMapper;
import com.fastdye.almacen.infrastructure.persistence.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaActivityRepositoryAdapter implements ActivityRepositoryPort {

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public Activity save(Activity activity) {
        ActivityEntity entity = ActivityMapper.toEntity(activity);
        return ActivityMapper.toModel(activityRepository.save(entity));
    }

    @Override
    public Optional<Activity> findById(int id) {
        return activityRepository.findById(id)
                .map(ActivityMapper::toModel);
    }

    @Override
    public List<Activity> findAll() {
        return activityRepository.findAllByOrderByFechaCreacionDesc()
                .stream()
                .map(ActivityMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Page<Activity> findAllHeadersByClientName(String nombreCliente, Pageable pageable) {
        return activityRepository.findAllHeadersByClientName(nombreCliente, pageable)
                .map(ActivityMapper::toModelWithoutDetails);
    }
}
