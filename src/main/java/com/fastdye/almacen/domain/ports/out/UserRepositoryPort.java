package com.fastdye.almacen.domain.ports.out;

import com.fastdye.almacen.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    List<User> findByAll();
    Optional<User> findById(String id);
}
