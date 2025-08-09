package com.fastdye.almacen.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="ScalActividad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nroSerie")
    private String nroSerie;

    @Column(name = "nroGuia")
    private String nroGuia;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "activo")
    private int activo;

    @Column(name = "estado")
    private int estado;

    @Column(name = "usuaCrea")
    private String usuarioCreacion;

    @Column(name = "usuaModi")
    private String usuarioModifica;

    @Column(name = "fechaCrea")
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaModi")
    private LocalDateTime fechaModifica;

    @ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "c_codi_clie")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "idAlmacen", referencedColumnName = "c_codi_alm")
    private StoreEntity store;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<ActivityDetailEntity> details;
}
