package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.Article;
import com.fastdye.almacen.domain.ports.in.ArticleUseCase;
import com.fastdye.almacen.domain.ports.out.ArticleRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements ArticleUseCase {

    @Autowired
    ArticleRepositoryPort articleRepositoryPort;

    @Override
    public List<Article> findAll() {
        return articleRepositoryPort.findAll();
    }

    @Override
    public Optional<Article> findById(int id) {
        return articleRepositoryPort.findById(id);
    }
}
