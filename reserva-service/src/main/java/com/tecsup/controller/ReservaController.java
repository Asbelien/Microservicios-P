package com.tecsup.controller;

import com.tecsup.model.Reserva;
import com.tecsup.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<Reserva>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtener(@PathVariable Long id) {
        Reserva reserva = service.obtener(id);
        if (reserva == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/historial/{clienteId}")
    public ResponseEntity<List<Reserva>> historialPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.historialPorCliente(clienteId));
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(reserva));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Reserva> confirmar(@PathVariable Long id) {
        Reserva confirmada = service.confirmar(id);
        if (confirmada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(confirmada);
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        Reserva cancelada = service.cancelar(id);
        if (cancelada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cancelada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!service.eliminar(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}