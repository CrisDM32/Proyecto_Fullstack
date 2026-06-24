package com.hospital.gestion_de_citas.dto.response;

import com.hospital.gestion_de_citas.model.EstadoCita;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaResponse {

    private Long idCita;
    private Long idPaciente;
    private Long idProfesional;
    private Long idPago;
    private LocalDateTime fechaHoraCita;
    private String motivoConsulta;
    private EstadoCita estadoCita;

    private Long idAgenda;
}
