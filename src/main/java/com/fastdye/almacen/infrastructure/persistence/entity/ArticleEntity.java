package com.fastdye.almacen.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Scom_Scaidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "c_desc_scdcompra")
    private String nombreArticulo;

    @Column(name = "c_anula_reg")
    private int anulaReg;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ActivityDetailEntity> activityDetailEntityList;
}
