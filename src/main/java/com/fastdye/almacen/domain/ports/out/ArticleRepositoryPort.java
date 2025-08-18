package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleRepositoryPort {
    List<Article> findAll();
    Optional<Article> findById(int id);
    Page<Article> findAllPage(Pageable pageable);
    Page<Article> search(String q, Pageable pageable);
}
