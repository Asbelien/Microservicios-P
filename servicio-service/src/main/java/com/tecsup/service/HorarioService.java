package com.tecsup.service;

import com.tecsup.model.Horario;
import com.tecsup.repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;

    public List<Horario> listar() {
        return repository.findAll();
    }

    public List<Horario> listarPorServicio(Long servicioId) {
        return repository.findByServicioId(servicioId);
    }

    public Horario obtener(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Horario guardar(Horario horario) {
        return repository.save(horario);
    }

    public Horario actualizar(Long id, Horario horario) {
        Horario existente = repository.findById(id).orElse(null);
        if (existente == null) {
            return null;
        }
        existente.setServicioId(horario.getServicioId());
        existente.setFecha(horario.getFecha());
        existente.setHoraInicio(horario.getHoraInicio());
        existente.setDisponible(horario.isDisponible());
        return repository.save(existente);
    }

    public boolean eliminar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public Horario marcarComoNoDisponible(Long id) {
        Horario horario = repository.findById(id).orElse(null);
        if (horario == null) {
            return null;
        }
        horario.setDisponible(false);
        return repository.save(horario);
    }
}