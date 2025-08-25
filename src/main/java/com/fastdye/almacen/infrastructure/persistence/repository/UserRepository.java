package com.fastdye.almacen.infrastructure.persistence.repository;

import com.fastdye.almacen.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByIdUsuarioAndAnulaReg(String idUsuario, int anulaReg);
    List<UserEntity> findAllByAnulaReg(int anulaReg);
}
