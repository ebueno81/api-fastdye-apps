package com.fastdye.almacen.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ScalActividadDetalle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "idActividad", insertable = false, updatable = false)
    private int idActividad;

    @Column(name = "cajas")
    private int cajas;

    @Column(name = "peso")
    private double peso;

    @Column(name = "nroLote")
    private String lote;

    @Column(name = "activo")
    private int activo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idArticulo", referencedColumnName = "id")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idActividad", referencedColumnName = "id")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    @JsonBackReference
    private ActivityEntity activity;

}
