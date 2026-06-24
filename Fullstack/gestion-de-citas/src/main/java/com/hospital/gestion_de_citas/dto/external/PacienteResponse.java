package com.hospital.gestion_de_citas.dto.external;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PacienteResponse {
    private Long idPaciente;
    private String nombrePaciente;
    private int rut;
    private LocalDate fechaNacimiento;
    private String genero;
    private Integer telefono;
    private String correo;
    private String direccion;
    private String grupoSanguineo;

    private List<ContactoEmergenciaResponse> contactoEmergencia;
}