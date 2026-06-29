package com.tecsup.service;

import com.tecsup.entity.Pedido;
import com.tecsup.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(Long id, Pedido pedido) {
        Pedido existente = pedidoRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setClienteId(pedido.getClienteId());
        existente.setFecha(pedido.getFecha());
        existente.setTotal(pedido.getTotal());
        existente.setEstado(pedido.getEstado());
        return pedidoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }
}