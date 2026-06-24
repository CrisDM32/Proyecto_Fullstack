package com.hospital.gestion_de_citas.dto.response;

import com.hospital.gestion_de_citas.model.AsistenciaTurno;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AgendaMedicaResponse {

    private Long idAgenda;
    private LocalDate fecha;
    private String horaInicioTurno;
    private String horaFinTurno;
    private AsistenciaTurno asistenciaTurno;

    private Long idProfesional;
    private String nombreProfesional;

    private List<CitaResponse> citas;
}
