package com.fastdye.almacen.infrastructure.persistence.adapter;

import com.fastdye.almacen.domain.model.User;
import com.fastdye.almacen.domain.ports.out.UserRepositoryPort;
import com.fastdye.almacen.infrastructure.persistence.mapper.UserMapper;
import com.fastdye.almacen.infrastructure.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findByAll() {
        return userRepository.findAllByAnulaReg(0)
                .stream()
                .map(UserMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findByIdUsuarioAndAnulaReg(id,0)
                .map(UserMapper::toModel);
    }
}
