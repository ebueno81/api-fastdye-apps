package com.fastdye.almacen.infrastructure.rest.mapper;

import com.fastdye.almacen.domain.model.User;
import com.fastdye.almacen.infrastructure.rest.dto.UserResponseDto;

import java.util.List;

public class UserRestMapper {

    public static UserResponseDto toResponse(User user){
        return UserResponseDto.builder()
                .id(user.getId())
                .nombreUsuario(user.getNombreUsuario())
                .build();
    }

    public static List<UserResponseDto> toResponseList(List<User> users){
        return users.stream().map(UserRestMapper::toResponse).toList();
    }
}
