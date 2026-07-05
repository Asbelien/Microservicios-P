package com.tecsup.services;

import com.tecsup.client.CategoriaClient;
import com.tecsup.dto.CategoriaDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaClientWrapper {

    @Autowired
    private CategoriaClient categoriaClient;

    @CircuitBreaker(name = "categoriaService", fallbackMethod = "categoriaFallback")
    public CategoriaDTO obtenerCategoriaConFallback(Long categoriaId) {
        return categoriaClient.obtenerCategoria(categoriaId);
    }

    public CategoriaDTO categoriaFallback(Long categoriaId, Throwable t) {
        CategoriaDTO fallback = new CategoriaDTO();
        fallback.setId(categoriaId);
        fallback.setNombre("Categoría no disponible");
        return fallback;
    }
}