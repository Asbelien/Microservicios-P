package com.tecsup.service;

import com.tecsup.client.ClienteClient;
import com.tecsup.dto.ClienteDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteClientWrapper {

    @Autowired
    private ClienteClient clienteClient;

    @Retry(name = "clienteRetry", fallbackMethod = "clienteFallback")
    @CircuitBreaker(name = "clienteService", fallbackMethod = "clienteFallback")
    public ClienteDTO obtenerClienteConFallback(Long id) {
        System.out.println("Llamando a cliente-service...");
        return clienteClient.obtenerCliente(id);
    }

    public ClienteDTO clienteFallback(Long id, Throwable t) {
        System.out.println("Fallback ejecutado - cliente no disponible");
        ClienteDTO fallback = new ClienteDTO();
        fallback.setId(id);
        fallback.setNombre("Cliente no disponible");
        return fallback;
    }
}