package com.tecsup.service;

import com.tecsup.model.Servicio;
import com.tecsup.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository repository;

    public List<Servicio> listar() {
        return repository.findAll();
    }

    public Servicio obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Servicio guardar(Servicio servicio) {
        return repository.save(servicio);
    }

    public Servicio actualizar(Long id, Servicio servicio) {
        Servicio existente = repository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setNombre(servicio.getNombre());
        existente.setDuracionMinutos(servicio.getDuracionMinutos());
        existente.setPrecio(servicio.getPrecio());
        return repository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}