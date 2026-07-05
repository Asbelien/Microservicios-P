package com.tecsup.service;

import com.tecsup.dto.ProductoDTO;
import com.tecsup.entity.Pedido;

import java.util.List;

public interface PedidoService {

    List<Pedido> findAll();

    Pedido findById(Long id);

    Pedido save(Pedido pedido);

    Pedido update(Long id, Pedido pedido);

    void delete(Long id);

    ProductoDTO consultarProducto(Long productoId);
}