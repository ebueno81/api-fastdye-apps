package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.ports.in.ActivityUseCase;
import com.fastdye.almacen.infrastructure.rest.dto.UpsertActivityDetailsRequest;

import java.util.List;

public final class UpsertDetailsRestMapper {

    private UpsertDetailsRestMapper() {}

    public static ActivityUseCase.UpsertActivityDetailsCommand toCommand(UpsertActivityDetailsRequest req) {
        List<ActivityUseCase.UpsertActivityDetailsCommand.DetailToCreate> creates =
                req.getToCreate() == null ? List.of() :
                        req.getToCreate().stream()
                                .map(c -> new ActivityUseCase.UpsertActivityDetailsCommand.DetailToCreate(
                                        c.getIdArticulo(), c.getNroLote(), c.getPeso(), c.getCajas()
                                ))
                                .toList();

        List<ActivityUseCase.UpsertActivityDetailsCommand.DetailToUpdate> updates =
                req.getToUpdate() == null ? List.of() :
                        req.getToUpdate().stream()
                                .map(u -> new ActivityUseCase.UpsertActivityDetailsCommand.DetailToUpdate(
                                        u.getId(), u.getIdArticulo(), u.getNroLote(), u.getPeso(), u.getCajas()
                                ))
                                .toList();

        List<Integer> deletes = req.getToDelete() == null ? List.of() : req.getToDelete();

        return new ActivityUseCase.UpsertActivityDetailsCommand(creates, updates, deletes);
    }
}
