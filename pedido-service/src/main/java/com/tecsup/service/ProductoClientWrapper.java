package com.tecsup.service;

import com.tecsup.client.ProductoClient;
import com.tecsup.dto.ProductoDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoClientWrapper {

    @Autowired
    private ProductoClient productoClient;

    @Bulkhead(name = "productoBulkhead", fallbackMethod = "productoFallback")
    @Retry(name = "productoRetry", fallbackMethod = "productoFallback")
    @CircuitBreaker(name = "productoService")
    public ProductoDTO obtenerProductoConFallback(Long id) {
        System.out.println("Llamando a producto-service...");
        return productoClient.obtenerProducto(id);
    }

    public ProductoDTO productoFallback(Long id, Throwable t) {
        System.out.println("Fallback ejecutado");
        ProductoDTO fallback = new ProductoDTO();
        fallback.setId(id);
        fallback.setNombre("Servicio no disponible");
        fallback.setPrecio(0.0);
        fallback.setDuracionMinutos(0);
        return fallback;
    }
}