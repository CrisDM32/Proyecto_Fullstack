package com.example.Gestion_de_Citas.client;

import com.example.Gestion_de_Citas.dto.external.PacienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pacientes-service", url = "localhost:9002/api/v1/pacientes")
public interface PacientesClient {
    @GetMapping("/{id}")
    PacienteResponse getPaciente(@PathVariable Long id);
}

