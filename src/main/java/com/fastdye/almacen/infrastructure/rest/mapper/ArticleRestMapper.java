package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.Article;
import com.fastdye.almacen.infrastructure.rest.dto.ArticleResponseDto;

import java.util.List;

public class ArticleRestMapper {

    public static  ArticleResponseDto toResponse(Article article){
        return ArticleResponseDto.builder()
                .id(article.getId())
                .nombreArticulo(article.getNombreArticulo())
                .build();
    }

    public static List<ArticleResponseDto> toResponseList(List<Article> articles){
        return articles.stream().map(ArticleRestMapper::toResponse).toList();
    }
}
