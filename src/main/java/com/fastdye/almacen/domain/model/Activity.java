package com.fastdye.almacen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activity {
    private int id;
    private String nroSerie;
    private String nroGuia;
    private String observacion;
    private int activo;
    private int estado;
    private Client client;
    private Store store;
    private Reason reason;
    private String usuarioCreacion;
    private String usuarioModifica;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModifica;
    List<ActivityDetail> details;

    public double calculateTotalWeight(){
        return details.stream().mapToDouble(ActivityDetail::getPeso).sum();
    }
}
