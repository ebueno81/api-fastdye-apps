package com.fastdye.almacen.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Store {
    String id;
    String nombreAlmacen;
    Activity activity;
}
