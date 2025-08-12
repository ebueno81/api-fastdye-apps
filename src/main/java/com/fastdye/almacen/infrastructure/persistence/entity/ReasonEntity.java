package com.fastdye.almacen.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "scal_mtmov")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReasonEntity {

    @Id
    @Column(name = "c_codi_mt")
    private String idReason;

    @Column(name = "c_desc_mt")
    private String nameReason;

    @Column(name = "c_tpo_mov")
    private String typeReason;

    @Column(name = "c_anula_reg")
    private int active;
}
