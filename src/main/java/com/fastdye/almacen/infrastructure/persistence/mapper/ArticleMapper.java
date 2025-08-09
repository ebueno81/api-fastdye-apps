package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.Article;
import com.fastdye.almacen.domain.model.Store;
import com.fastdye.almacen.infrastructure.persistence.entity.ArticleEntity;
import com.fastdye.almacen.infrastructure.persistence.entity.StoreEntity;

public class ArticleMapper {
    public static ArticleEntity toEntity(Article model) {
        if (model == null) return null;

        return ArticleEntity.builder()
                .id(model.getId())
                .nombreArticulo(model.getNombreArticulo())
                .build();
    }

    public static Article toModel(ArticleEntity entity) {
        if (entity == null) return null;

        return Article.builder()
                .id(entity.getId())
                .nombreArticulo(entity.getNombreArticulo())
                .build();
    }
}
