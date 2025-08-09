package com.fastdye.almacen.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Scal_Almacen")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreEntity {

    @Id
    @Column(name="c_codi_alm")
    private String idAlmacen;

    @Column(name = "c_desc_alm")
    private String nombreAlmacen;

    @Column(name = "c_anula_reg")
    private int anulaReg;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<ActivityEntity> activityEntityList;

}
