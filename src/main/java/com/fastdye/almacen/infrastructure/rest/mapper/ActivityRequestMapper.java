package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.*;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityRequest;

public class ActivityRequestMapper {

    public static Activity toModel(ActivityRequest dto) {
        return Activity.builder()
                .nroSerie(dto.getNroSerie())
                .nroGuia(dto.getNroGuia())
                .observacion(dto.getObservacion())
                .client(Client.builder().id(dto.getIdCliente()).build())
                .store(Store.builder().id(dto.getIdAlmacen()).build())
                .reason(dto.getIdReason() == null ? null :
                        Reason.builder().idReason(dto.getIdReason()).build())
                .usuarioCreacion(dto.getUsuarioCreacion())
                .usuarioModifica(dto.getUsuarioModifica())
                .fechaCreacion(dto.getFechaCreacion())
                .details(dto.getDetalles().stream()
                        .map(d -> ActivityDetail.builder()
                                .idArticulo(d.getIdArticulo())
                                .nroLote(d.getNroLote())
                                .peso(d.getPeso())
                                .cajas(d.getCajas())
                                .build())
                        .toList())
                .build();
    }
}
