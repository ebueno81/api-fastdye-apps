package com.fastdye.almacen.infrastructure.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponseDto {
    private int id;
    private String nombreArticulo;
}
