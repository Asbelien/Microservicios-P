package com.tecsup.service;

import com.tecsup.client.ProductoClient;
import com.tecsup.dto.ProductoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductoClientWrapper {

    @Autowired
    private ProductoClient productoClient;

    @CircuitBreaker(name = "productoService", fallbackMethod = "productoFallback")
    public ProductoDTO obtenerProductoConFallback(Long id) {
        return productoClient.obtenerProducto(id);
    }

    public ProductoDTO productoFallback(Long id, Throwable t) {
        ProductoDTO fallback = new ProductoDTO();
        fallback.setId(id);
        fallback.setNombre("Producto no disponible");
        fallback.setPrecio(0.0);
        fallback.setStock(0);
        return fallback;
    }
}