package com.tecsup.services;

import com.tecsup.dto.CategoriaDTO;
import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repo;

    @Autowired
    private CategoriaClientWrapper categoriaClientWrapper;

    public List<Producto> listar() {
        List<Producto> productos = repo.findAll();
        productos.forEach(producto -> {
            CategoriaDTO categoria = categoriaClientWrapper.obtenerCategoriaConFallback(producto.getCategoriaId());
            producto.setCategoria(categoria);
        });
        return productos;
    }

    public Producto guardar(Producto p) {
        Producto producto = repo.save(p);
        CategoriaDTO categoria = categoriaClientWrapper.obtenerCategoriaConFallback(producto.getCategoriaId());
        producto.setCategoria(categoria);
        return producto;
    }

    public Producto obtener(Long id) {
        Producto producto = repo.findById(id).orElse(null);
        if (producto != null) {
            CategoriaDTO categoria = categoriaClientWrapper.obtenerCategoriaConFallback(producto.getCategoriaId());
            producto.setCategoria(categoria);
        }
        return producto;
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}