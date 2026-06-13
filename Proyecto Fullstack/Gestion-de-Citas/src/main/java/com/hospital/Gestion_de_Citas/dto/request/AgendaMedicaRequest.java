package com.hospital.Gestion_de_Citas.dto.request;

import com.hospital.Gestion_de_Citas.model.AsistenciaTurno;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AgendaMedicaRequest {

    @NotNull(message = "El id del profesional es obligatoio.")
    private Long idProfesional;

    @NotNull(message = "La fecha es obligatoria.")
    private LocalDate fecha;

    @NotNull(message = "El horario de inicio de turno es obligatorio.")
    private LocalDateTime horaInicioTurno;

    @NotNull(message = "El horario de fin de turno es obligatorio.")
    private LocalDateTime horaFinTurno;

    @NotNull(message = "Debe especificar si estubo presente o ausente en el turno.")
    private AsistenciaTurno asistenciaTurno;
}
