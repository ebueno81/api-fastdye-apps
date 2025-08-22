package com.fastdye.almacen.infrastructure.rest.controller;

import com.fastdye.almacen.infrastructure.realtime.ActivityEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.io.IOException;
import java.time.Duration;

@RestController
@RequestMapping("/activities")
public class ActivityStreamController {

    @Autowired
    Sinks.Many<ActivityEventBus.ProcessActivityEvent> sink;

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter(0L); // sin timeout
        // Suscríbete al sink + heartbeat
        Flux<ActivityEventBus.ProcessActivityEvent> flux = sink.asFlux()
                .mergeWith(Flux.interval(Duration.ofSeconds(15))
                        .map(t -> new ActivityEventBus.ProcessActivityEvent(-1, -1, "HEARTBEAT")));

        Disposable subscription = flux.subscribe(ev -> {
                    try {
                        emitter.send(SseEmitter.event()
                                .name("activity")
                                .data(ev)); // se serializa a JSON automáticamente
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }
                }, err -> emitter.completeWithError(err),
                emitter::complete);

        emitter.onTimeout(() -> {
            subscription.dispose();
            emitter.complete();
        });
        emitter.onCompletion(subscription::dispose);

        return emitter;
    }

    @GetMapping(path = "/{id}/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamById(@PathVariable int id) {
        SseEmitter emitter = new SseEmitter(0L);
        Disposable subscription = sink.asFlux()
                .filter(ev -> ev.activityId() == id)
                .subscribe(ev -> {
                            try {
                                emitter.send(SseEmitter.event().name("activity").data(ev));
                            } catch (IOException e) {
                                emitter.completeWithError(e);
                            }
                        }, err -> emitter.completeWithError(err),
                        emitter::complete);

        emitter.onTimeout(() -> {
            subscription.dispose();
            emitter.complete();
        });
        emitter.onCompletion(subscription::dispose);
        return emitter;
    }
}
