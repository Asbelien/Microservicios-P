package com.tecsup.dto;

public class ClienteDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String estado;

    public ClienteDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
EOF2. ClienteClient (Feign, nuevo)
bashcat > pedido-service/src/main/java/com/tecsup/client/ClienteClient.java << 'EOF'
package com.tecsup.client;

import com.tecsup.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente-service")
public interface ClienteClient {

    @GetMapping("/api/clientes/{id}")
    ClienteDTO obtenerCliente(@PathVariable Long id);
}
