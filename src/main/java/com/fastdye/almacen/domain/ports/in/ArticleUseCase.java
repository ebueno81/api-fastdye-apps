package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleUseCase {
    List<Article> findAll();
    Optional<Article> findById(int id);
}
