package com.fastdye.almacen.infrastructure.config;

import com.fastdye.almacen.domain.ports.in.AuthUseCase;
import com.fastdye.almacen.domain.ports.in.UserUseCase;
import com.fastdye.almacen.domain.services.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public AuthUseCase authUseCase(UserUseCase userUseCase) {
        return new AuthService(userUseCase);
    }
}
