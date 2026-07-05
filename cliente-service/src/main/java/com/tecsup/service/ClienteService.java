package com.tecsup.service;

import com.tecsup.dto.PedidoDTO;
import com.tecsup.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();

    Cliente findById(Long id);

    Cliente save(Cliente cliente);

    Cliente update(Long id, Cliente cliente);

    void delete(Long id);

    List<PedidoDTO> obtenerPedidosDeCliente(Long clienteId);
}