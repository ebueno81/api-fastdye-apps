package com.fastdye.almacen.infrastructure.persistence.mapper;

import com.fastdye.almacen.domain.model.User;
import com.fastdye.almacen.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User model) {
        if (model == null) return null;

        return UserEntity.builder()
                .idUsuario(model.getId())
                .nombreUsuario(model.getNombreUsuario())
                .claveUsuario(model.getClave())
                .anulaReg(model.getActivo()) // Ojo aquí si lo manejas invertido
                .build();
    }

    public static User toModel(UserEntity entity) {
        if (entity == null) return null;

        return User.builder()
                .id(entity.getIdUsuario())
                .nombreUsuario(entity.getNombreUsuario())
                .clave(entity.getClaveUsuario())
                .activo(entity.getAnulaReg()) // Ojo aquí si lo manejas invertido
                .build();
    }
}
