package com.fastdye.almacen.infrastructure.persistence.repository;

import com.fastdye.almacen.infrastructure.persistence.entity.ClientEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<ClientEntity,String> {
    @Query("""
           select c
           from ClientEntity c
           where lower(c.nombreCliente) like lower(concat('%', :q, '%'))
              or lower(c.idCliente)      like lower(concat('%', :q, '%'))
           """)
    Page<ClientEntity> search(@Param("q") String q, Pageable pageable);
}
