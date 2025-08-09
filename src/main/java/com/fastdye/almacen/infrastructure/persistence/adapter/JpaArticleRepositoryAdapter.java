package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.Article;
import com.fastdye.almacen.domain.ports.out.ArticleRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.mapper.ArticleMapper;
import com.fastdye.almacen.infrastructure.persistence.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaArticleRepositoryAdapter implements ArticleRepositoryPort {

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Article> findById(int id) {
        return articleRepository.findById(id)
                .map(ArticleMapper::toModel);
    }
}
