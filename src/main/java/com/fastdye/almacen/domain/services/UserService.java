package com.fastdye.almacen.domain.services;

import com.fastdye.almacen.domain.model.User;
import com.fastdye.almacen.domain.ports.in.UserUseCase;
import com.fastdye.almacen.domain.ports.out.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    @Autowired
    UserRepositoryPort userRepositoryPort;

    @Override
    public List<User> findByAll() {
        return userRepositoryPort.findByAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepositoryPort.findById(id);
    }
}
