package com.fastdye.almacen.infrastructure.persistence.repository;

import com.fastdye.almacen.infrastructure.persistence.entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

    @Query("""
            select a
            from ArticleEntity a
            where lower(a.nombreArticulo) like lower(concat('%', :q, '%'))
            and a.codigoTg='01' and a.opcNoInventario=0 and a.anulaReg=0
            """)
    Page<ArticleEntity> search(@Param("q") String q, Pageable pageable);

    @Query("""
            select a
            from ArticleEntity a
            where a.codigoTg='01' and a.opcNoInventario=0 and a.anulaReg=0
            """)
    Page<ArticleEntity> findByActivos(Pageable pageable);
}
