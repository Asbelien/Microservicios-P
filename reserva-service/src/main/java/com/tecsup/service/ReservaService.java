package com.tecsup.service;

import com.tecsup.dto.ClienteDTO;
import com.tecsup.dto.HorarioDTO;
import com.tecsup.model.Reserva;
import com.tecsup.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private ClienteClientWrapper clienteClientWrapper;

    @Autowired
    private ServicioClientWrapper servicioClientWrapper;

    public List<Reserva> listar() {
        return repository.findAll();
    }

    public Reserva obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Reserva> historialPorCliente(Long clienteId) {
        return repository.findByClienteId(clienteId);
    }

    public Reserva crear(Reserva reserva) {
        // Valida que el cliente exista (con Circuit Breaker + Retry + fallback)
        ClienteDTO cliente = clienteClientWrapper.obtenerClienteConFallback(reserva.getClienteId());

        // Valida que el horario exista y esté disponible (con Circuit Breaker + Retry + fallback)
        HorarioDTO horario = servicioClientWrapper.obtenerHorarioConFallback(reserva.getHorarioId());

        if (cliente == null || cliente.getNombre().equals("Cliente no disponible")) {
            reserva.setEstado("PENDIENTE_VALIDACION_CLIENTE");
        } else if (horario == null || !horario.isDisponible()) {
            reserva.setEstado("PENDIENTE_VALIDACION_HORARIO");
        } else {
            reserva.setEstado("PENDIENTE");
        }

        return repository.save(reserva);
    }

    public Reserva confirmar(Long id) {
        Reserva reserva = repository.findById(id).orElse(null);
        if (reserva == null) {
            return null;
        }
        reserva.setEstado("CONFIRMADA");
        return repository.save(reserva);
    }

    public Reserva cancelar(Long id) {
        Reserva reserva = repository.findById(id).orElse(null);
        if (reserva == null) {
            return null;
        }
        reserva.setEstado("CANCELADA");
        return repository.save(reserva);
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}