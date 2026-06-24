package com.hospital.gestion_de_citas.client;

import com.hospital.gestion_de_citas.dto.external.PacienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gestion-de-pacientes",path = "/api/v1/pacientes")
public interface PacientesClient {
    @GetMapping("/{id}")
    PacienteResponse getPaciente(@PathVariable Long id);
}
