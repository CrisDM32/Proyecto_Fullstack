package com.hospital.Pagos.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CitaResponse {
    private Long idCita;
    private String nombrePaciente;

    private List<PagoResponse> pagos;
}
