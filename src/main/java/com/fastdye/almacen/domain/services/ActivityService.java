package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.domain.ports.in.ActivityUseCase;
import com.fastdye.almacen.domain.ports.out.ActivityRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements ActivityUseCase {

    @Autowired
    ActivityRepositoryPort activityRepositoryPort;

    public ActivityService(ActivityRepositoryPort activityRepositoryPort) {
        this.activityRepositoryPort = activityRepositoryPort;
    }

    @Override
    public Activity registrar(Activity activity) {
        return activityRepositoryPort.save(activity);
    }

    @Override
    public Optional<Activity> buscarPorId(int id) {
        return activityRepositoryPort.findById(id);
    }

    @Override
    public List<Activity> listar() {
        return activityRepositoryPort.findAll();
    }
}