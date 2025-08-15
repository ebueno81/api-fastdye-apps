package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.*;
import com.fastdye.almacen.domain.ports.in.ActivityUseCase;
import com.fastdye.almacen.domain.ports.out.ActivityRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService implements ActivityUseCase {

    @Autowired
    ActivityRepositoryPort activityRepositoryPort;

    @Override
    public Activity registrar(Activity activity) {
        return activityRepositoryPort.save(activity);
    }

    @Override
    public Optional<Activity> buscarPorId(int id) {
        return activityRepositoryPort.findById(id);
    }

    @Override
    public List<Activity> listar() {
        return activityRepositoryPort.findAll();
    }

    @Override
    public Activity actualizarCabecera(int id, UpdateActivityHeaderCommand cmd) {
        Activity current = activityRepositoryPort.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Actividad " + id + " no existe"));

        current.setNroSerie(cmd.nroSerie());
        current.setNroGuia(cmd.nroGuia());
        current.setObservacion(cmd.observacion());
        current.setUsuarioModifica(cmd.usuarioModifica());

        // El dominio usa IDs; el adapter JPA resolverá referencias con getReferenceById
        current.setClient(Client.builder().id(cmd.idCliente()).build());
        current.setStore(Store.builder().id(cmd.idAlmacen()).build());
        current.setReason(cmd.idReason() == null ? null : Reason.builder().idReason(cmd.idReason()).build());

        return activityRepositoryPort.save(current);
    }

    @Override
    public Activity upsertDetalles(int id, UpsertActivityDetailsCommand cmd) {
        Activity current = activityRepositoryPort.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Actividad " + id + " no existe"));

        // Indexar existentes por ID
        Map<Integer, ActivityDetail> byId = current.getDetails().stream()
                .collect(Collectors.toMap(ActivityDetail::getId, d -> d));

        // DELETE
        if (cmd.toDelete() != null && !cmd.toDelete().isEmpty()) {
            current.getDetails().removeIf(d -> cmd.toDelete().contains(d.getId()));
        }

        // UPDATE
        if (cmd.toUpdate() != null) {
            for (var u : cmd.toUpdate()) {
                ActivityDetail ex = byId.get(u.id());
                if (ex == null) continue; // o lanzar 404

                if (u.idArticulo() != null && u.idArticulo() > 0) {
                    ex.setIdArticulo(u.idArticulo());
                }
                ex.setNroLote(u.nroLote());
                ex.setPeso(u.peso());
                ex.setCajas(u.cajas());
            }
        }

        // CREATE
        if (cmd.toCreate() != null) {
            for (var c : cmd.toCreate()) {
                if (c.idArticulo() == null || c.idArticulo() <= 0) {
                    throw new IllegalArgumentException("Debe especificar un artículo válido para crear el detalle");
                }
                ActivityDetail nd = ActivityDetail.builder()
                        .idArticulo(c.idArticulo())
                        .nroLote(c.nroLote())
                        .peso(c.peso())
                        .cajas(c.cajas())
                        .build();
                current.getDetails().add(nd);
            }
        }

        return activityRepositoryPort.save(current);
    }

    @Override
    public Page<Activity> listarSoloCabecera(String nombreCliente, Pageable pageable) {
        return activityRepositoryPort.findAllHeadersByClientName(nombreCliente,pageable);
    }

}