package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArticleUseCase {
    List<Article> findAll();
    Optional<Article> findById(int id);
    Page<Article> findAllPage(Pageable pageable);
    Page<Article> search(String q, Pageable pageable);
}
