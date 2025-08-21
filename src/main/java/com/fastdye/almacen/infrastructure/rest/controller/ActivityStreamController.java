package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.infrastructure.realtime.ActivityEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/activities")
public class ActivityStreamController {

    @Autowired
    Sinks.Many<ActivityEventBus.ProcessActivityEvent> sink;

    // Stream global de eventos
    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ActivityEventBus.ProcessActivityEvent> stream() {
        // Opcional: heartbeat para mantener viva la conexión
        return sink.asFlux()
                .mergeWith(Flux.interval(Duration.ofSeconds(15))
                        .map(t -> new ActivityEventBus.ProcessActivityEvent(-1, -1, "HEARTBEAT")));
    }

    // (Opcional) Stream por actividad concreta
    @GetMapping(path = "/{id}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ActivityEventBus.ProcessActivityEvent> streamById(@PathVariable int id) {
        return sink.asFlux().filter(ev -> ev.activityId() == id);
    }
}
