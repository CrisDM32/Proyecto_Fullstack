package com.hospital.gestion_de_pacientes.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactoEmergenciaRequest {

    @NotNull(message = "El id del Paciente es obligatorio.")
    private Long idPaciente;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 carácteres.")
    private String nombreContactoEmergencia;

    @NotNull(message = "El telefono del contacto de emergencia es obligatorio.")
    private int telefonoContactoEmergencia;
}