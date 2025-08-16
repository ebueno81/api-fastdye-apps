package com.fastdye.almacen.infrastructure.persistence.repository;

import com.fastdye.almacen.infrastructure.persistence.entity.ActivityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityEntity, Integer> {

    List<ActivityEntity> findAllByOrderByFechaCreacionDesc();

    @Query("SELECT DISTINCT a FROM ActivityEntity a " +
            "JOIN a.client c " +
            "LEFT JOIN FETCH a.details d " +
            "WHERE (:nombreCliente IS NULL OR c.nombreCliente LIKE %:nombreCliente%)")
    Page<ActivityEntity> findAllHeadersByClientName(@Param("nombreCliente") String nombreCliente,
                                                    Pageable pageable);


}
