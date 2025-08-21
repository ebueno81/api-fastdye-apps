package com.fastdye.almacen.infrastructure.realtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class ActivityEventBus {

    @Bean
    public Sinks.Many<ProcessActivityEvent> activitySink() {
        // multicast: todos los suscriptores reciben; buffer para backpressure
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    public record ProcessActivityEvent(int activityId, int idIngresoCreado, String status) {}
}
