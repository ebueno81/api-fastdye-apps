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
@Table(name = "Scal_Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "c_codi_usua")
    private String idUsuario;

    @Column(name = "c_nom_usua")
    private String nombreUsuario;

    @Column(name = "c_anula_reg")
    private int anulaReg;

    @Column(name = "c_clave_usua")
    private String claveUsuario;
}
