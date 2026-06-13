package com.hospital.Gestion_de_Citas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesionalResponse {

    private Long idProfesional;
    private int rut;
    private String nombreProfesional;
    private String especialidad;
    private int telefono;
    private String correo;
    private String direccion;
    private int numLicencia;
    private LocalDate fechaContratacion;

    private List<AgendaMedicaResponse> agendasMedicas;
}
