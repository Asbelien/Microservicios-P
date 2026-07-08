package com.tecsup.repository;

import com.tecsup.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioRepository extends JpaRepository<Horario, Long> {
    List<Horario> findByServicioId(Long servicioId);
}