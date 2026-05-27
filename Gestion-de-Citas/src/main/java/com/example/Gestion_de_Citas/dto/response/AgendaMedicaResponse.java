package com.example.Gestion_de_Citas.dto.response;

import com.example.Gestion_de_Citas.model.AsistenciaTurno;
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
