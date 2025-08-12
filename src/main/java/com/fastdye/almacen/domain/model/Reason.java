package com.fastdye.almacen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reason {
    private String idReason;
    private String nameReason;
    private String typeReason;
    private int active;
}
