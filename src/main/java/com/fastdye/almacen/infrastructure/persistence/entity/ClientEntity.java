package com.fastdye.almacen.infrastructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sca_cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @Column(name = "c_codi_clie")
    private String idCliente;

    @Column(name = "c_desc_clie")
    private String nombreCliente;

    @Column(name = "c_anula_reg")
    private int anulaReg;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ActivityEntity> actividades;
}
