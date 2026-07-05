package com.tecsup.service;

import com.tecsup.client.PedidoClient;
import com.tecsup.dto.PedidoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PedidoClientWrapper {

    @Autowired
    private PedidoClient pedidoClient;

    @CircuitBreaker(name = "pedidoService", fallbackMethod = "pedidosFallback")
    public List<PedidoDTO> obtenerPedidosConFallback() {
        return pedidoClient.obtenerTodosLosPedidos();
    }

    public List<PedidoDTO> pedidosFallback(Throwable t) {
        return Collections.emptyList();
    }
}