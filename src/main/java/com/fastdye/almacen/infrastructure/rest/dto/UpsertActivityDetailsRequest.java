package com.fastdye.almacen.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpsertActivityDetailsRequest {
    private List<DetailToCreate> toCreate;
    private List<DetailToUpdate> toUpdate;
    private List<Integer> toDelete;

    @Data
    public static class DetailToCreate {
        private Integer idArticulo;
        private String nroLote;
        private Double peso;
        private Integer cajas;
    }

    @Data
    public static class DetailToUpdate {
        private Integer id;
        private Integer idArticulo;
        private String nroLote;
        private Double peso;
        private Integer cajas;
    }
}
