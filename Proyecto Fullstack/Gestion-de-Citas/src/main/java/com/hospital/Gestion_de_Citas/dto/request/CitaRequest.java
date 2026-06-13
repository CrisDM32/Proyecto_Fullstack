package com.hospital.Gestion_de_Citas.dto.request;

import com.hospital.Gestion_de_Citas.model.EstadoCita;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaRequest {

    @NotNull(message = "El id del paciente es obligatorio.")
    private Long idPaciente;

    @NotNull(message = "El id del profesional es obligatorio.")
    private Long idProfesional;

    @NotNull(message = "El id de la Agenda es obligatorio.")
    private Long idAgenda;

    @NotNull(message = "El id del pago es obligatorio.")
    private Long idPago;

    @NotNull(message = "La fecha y hora de la cita es obligatoria.")
    private LocalDateTime fechaHoraCita;

    @NotBlank(message = "El motivo de la consulta es obligatorio.")
    @Size(min = 1, max = 200, message = "El motivo de la consulta debe contener entre 1 y 200 carácteres.")
    private String motivoConsulta;

    @NotNull(message = "El estado de la cita es obligatorio.")
    private EstadoCita estadoCita;

}
