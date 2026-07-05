package com.tecsup.service;

import com.tecsup.dto.PedidoDTO;
import com.tecsup.entity.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoClientWrapper pedidoClientWrapper;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        Cliente existente = clienteRepository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setNombre(cliente.getNombre());
        existente.setApellido(cliente.getApellido());
        existente.setEmail(cliente.getEmail());
        existente.setTelefono(cliente.getTelefono());
        existente.setEstado(cliente.getEstado());
        return clienteRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<PedidoDTO> obtenerPedidosDeCliente(Long clienteId) {
        List<PedidoDTO> todosLosPedidos = pedidoClientWrapper.obtenerPedidosConFallback();
        return todosLosPedidos.stream()
                .filter(pedido -> clienteId.equals(pedido.getClienteId()))
                .collect(Collectors.toList());
    }
}