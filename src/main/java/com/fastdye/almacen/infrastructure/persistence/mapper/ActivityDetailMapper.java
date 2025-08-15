package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityDetailEntity;
import com.fastdye.almacen.infrastructure.persistence.entity.ArticleEntity;
import jakarta.persistence.EntityNotFoundException;

public class ActivityDetailMapper {

    public static ActivityDetailEntity toEntity(ActivityDetail model) {
        if (model == null) return null;

        ActivityDetailEntity entity = ActivityDetailEntity.builder()
                .id(model.getId())
                .idActividad(model.getIdActividad())
                .cajas(model.getCajas())
                .peso(model.getPeso())
                .lote(model.getNroLote())
                .activo(1)
                .build();

        if (model.getIdArticulo() != null && model.getIdArticulo() > 0) {
            ArticleEntity article = new ArticleEntity();
            article.setId(model.getIdArticulo());
            entity.setArticle(article);
        }

        return entity;
    }

    public static ActivityDetail toModel(ActivityDetailEntity entity) {
        if (entity == null) return null;

        ActivityDetail.ActivityDetailBuilder builder = ActivityDetail.builder()
                .id(entity.getId())
                .idActividad(entity.getIdActividad())
                .cajas(entity.getCajas())
                .peso(entity.getPeso())
                .nroLote(entity.getLote());

        if (entity.getArticle() != null && entity.getArticle().getId() != null && entity.getArticle().getId() > 0) {
            try {
                builder.idArticulo(entity.getArticle().getId());
                builder.nombreArticulo(entity.getArticle().getNombreArticulo());
            } catch (EntityNotFoundException e) {
                // Si Hibernate no puede inicializar el proxy porque no existe en DB
                builder.idArticulo(null);
                builder.nombreArticulo(null);
            }
        }

        return builder.build();
    }
}
