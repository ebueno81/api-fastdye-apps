package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.ActivityDetail;
import java.util.Optional;

public interface ActivityDetailRepositoryPort {
    ActivityDetail save(ActivityDetail detail);
    Optional<ActivityDetail> findById(int id);
}
