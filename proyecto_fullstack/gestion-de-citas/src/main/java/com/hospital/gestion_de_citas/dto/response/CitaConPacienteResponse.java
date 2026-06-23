package com.hospital.gestion_de_citas.dto.response;

import com.hospital.gestion_de_citas.dto.external.PacienteResponse;
import com.hospital.gestion_de_citas.model.EstadoCita;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaConPacienteResponse {

    private Long idCita;
    private PacienteResponse pacienteResponse;
    private Long idProfesional;
    private Long idPago;
    private LocalDateTime fechaHoraCita;
    private String motivoConsulta;
    private EstadoCita estadoCita;

    private Long idAgenda;
}
