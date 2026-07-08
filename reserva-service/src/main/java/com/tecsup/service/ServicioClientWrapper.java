package com.tecsup.service;

import com.tecsup.client.ServicioClient;
import com.tecsup.dto.HorarioDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServicioClientWrapper {

    @Autowired
    private ServicioClient servicioClient;

    @Retry(name = "servicioRetry", fallbackMethod = "horarioFallback")
    @CircuitBreaker(name = "servicioService", fallbackMethod = "horarioFallback")
    public HorarioDTO obtenerHorarioConFallback(Long id) {
        System.out.println("Llamando a servicio-service...");
        return servicioClient.obtenerHorario(id);
    }

    public HorarioDTO horarioFallback(Long id, Throwable t) {
        System.out.println("Fallback ejecutado - horario no disponible");
        HorarioDTO fallback = new HorarioDTO();
        fallback.setId(id);
        fallback.setDisponible(false);
        return fallback;
    }
}