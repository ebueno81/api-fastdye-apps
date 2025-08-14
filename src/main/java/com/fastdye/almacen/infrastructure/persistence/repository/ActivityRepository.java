package com.fastdye.almacen.infrastructure.persistence.repository;

import com.fastdye.almacen.infrastructure.persistence.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Integer> {
    List<ActivityEntity> findAllByOrderByFechaCreacionDesc();
}
