package com.fastdye.almacen.infrastructure.persistence.repository;

import com.fastdye.almacen.infrastructure.persistence.entity.ActivityDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityDetailRepository extends JpaRepository<ActivityDetailEntity, Integer> {
}
