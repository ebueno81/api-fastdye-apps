package com.fastdye.almacen.domain.ports.in;

import com.fastdye.almacen.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserUseCase {
    List<User> findByAll();
    Optional<User> findById(String id);
}
