package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.ActivityDetail;
import com.fastdye.almacen.infrastructure.persistence.entity.ActivityDetailEntity;
import com.fastdye.almacen.infrastructure.persistence.entity.ArticleEntity;

public class ActivityDetailMapper {

    public static ActivityDetailEntity toEntity(ActivityDetail model) {
        if (model == null) return null;

        return ActivityDetailEntity.builder()
                .id(model.getId())
                .cajas(model.getCajas())
                .peso(model.getPeso())
                .lote(model.getNroLote())
                .article(ArticleEntity.builder()
                        .id(model.getIdArticulo())
                        .build())
                .activo(1)
                .build();
    }

    public static ActivityDetail toModel(ActivityDetailEntity entity) {
        if (entity == null) return null;

        return ActivityDetail.builder()
                .id(entity.getId())
                .cajas(entity.getCajas())
                .peso(entity.getPeso())
                .nroLote(entity.getLote())
                .idArticulo(entity.getArticle() != null ? entity.getArticle().getId() : 0)
                .nombreArticulo(
                        entity.getArticle() != null ? entity.getArticle().getNombreArticulo() : null
                )
                .build();
    }
}
