package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.domain.ports.in.ArticleUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.ArticleResponseDto;
import com.fastdye.almacen.infrastructure.rest.mapper.ArticleRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    ArticleUseCase articleUseCase;

    @GetMapping
    @Operation(summary = "Listar todos los articulos")
    public ResponseEntity<List<ArticleResponseDto>> findAll(){
        var articles = articleUseCase.findAll();
        return ResponseEntity.ok(ArticleRestMapper.toResponseList(articles));
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
