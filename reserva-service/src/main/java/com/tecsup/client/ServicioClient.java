package com.tecsup.client;

import com.tecsup.dto.HorarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-service")
public interface ServicioClient {

    @GetMapping("/api/servicios/horarios/{id}")
    HorarioDTO obtenerHorario(@PathVariable Long id);
}