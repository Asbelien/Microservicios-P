package com.tecsup.services;

import com.tecsup.client.CategoriaClient;
import com.tecsup.dto.CategoriaDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaClientWrapper {

    @Autowired
    private CategoriaClient categoriaClient;

    @Bulkhead(name = "categoriaBulkhead", fallbackMethod = "categoriaFallback")
    @Retry(name = "categoriaRetry", fallbackMethod = "categoriaFallback")
    @CircuitBreaker(name = "categoriaService")
    public CategoriaDTO obtenerCategoriaConFallback(Long categoriaId) {
        System.out.println("Llamando a categoria-service...");
        return categoriaClient.obtenerCategoria(categoriaId);
    }

    public CategoriaDTO categoriaFallback(Long categoriaId, Throwable t) {
        System.out.println("Fallback ejecutado");
        CategoriaDTO fallback = new CategoriaDTO();
        fallback.setId(categoriaId);
        fallback.setNombre("Categoría no disponible");
        return fallback;
    }
}
