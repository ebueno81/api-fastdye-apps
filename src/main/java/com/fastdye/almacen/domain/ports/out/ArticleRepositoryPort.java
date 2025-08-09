package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryPort {
    List<Article> findAll();
    Optional<Article> findById(int id);
}
