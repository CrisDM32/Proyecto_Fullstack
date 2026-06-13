package com.hospital.Gestion_de_Citas.dto.response;

import com.hospital.Gestion_de_Citas.model.EstadoCita;
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
