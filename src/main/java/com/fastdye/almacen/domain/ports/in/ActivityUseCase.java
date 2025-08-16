package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.Activity;
import com.fastdye.almacen.infrastructure.rest.dto.ActivityHeaderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ActivityUseCase {
    Activity registrar(Activity activity);
    Optional<Activity> buscarPorId(int id);
    List<Activity> listar();

    Activity actualizarCabecera(int id, UpdateActivityHeaderCommand cmd);
    Activity upsertDetalles(int id, UpsertActivityDetailsCommand cmd);

    Page<ActivityHeaderDto> listarSoloCabecera(String nombreCliente, Pageable pageable);


    // Commands simples (parte del port in, no de REST)
    public static record UpdateActivityHeaderCommand(
            String nroSerie,
            String nroGuia,
            String observacion,
            String idCliente,
            String idAlmacen,
            String idReason,
            String usuarioModifica
    ) {}

    public static record UpsertActivityDetailsCommand(
            java.util.List<DetailToCreate> toCreate,
            java.util.List<DetailToUpdate> toUpdate,
            java.util.List<Integer> toDelete
    ) {
        public static record DetailToCreate(
                Integer idArticulo, String nroLote, Double peso, Integer cajas
        ) {}
        public static record DetailToUpdate(
                Integer id, Integer idArticulo, String nroLote, Double peso, Integer cajas
        ) {}
    }
}
