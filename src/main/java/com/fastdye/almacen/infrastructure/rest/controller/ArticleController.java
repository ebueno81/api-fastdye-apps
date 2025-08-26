package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.model.Article;
import com.fastdye.almacen.domain.ports.in.ArticleUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.ArticleResponseDto;
import com.fastdye.almacen.infrastructure.rest.mapper.ArticleRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    ArticleUseCase articleUseCase;

    @GetMapping("/page")
    @Operation(summary = "Listar todos los articulos")
    public ResponseEntity<List<ArticleResponseDto>> findAll(){
        var articles = articleUseCase.findAll();
        return ResponseEntity.ok(ArticleRestMapper.toResponseList(articles));
    }

    @GetMapping
    @Operation(summary = "Listar todos los registros de manera paginada")
    public ResponseEntity<Page<ArticleResponseDto>> findAllPage(
            @RequestParam(value="q", required = false) String q,
            @ParameterObject
            @PageableDefault(size = 20)Pageable pageable
            ){
        Page<Article> articles = (q == null || q.isBlank())
                ? articleUseCase.findAllPage(pageable)
                : articleUseCase.search(q.trim(), pageable);

        return ResponseEntity.ok(articles.map(ArticleRestMapper::toResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por id")
    public  ResponseEntity<ArticleResponseDto> findById(int id){
        return articleUseCase.findById(id)
                .map(ArticleRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
