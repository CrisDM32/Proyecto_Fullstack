package com.hospital.gestion_de_citas.dto.external;

import lombok.Data;

@Data
public class ContactoEmergenciaResponse {
    private Long idContactoEmergencia;
    private String nombreContactoEmergencia;
    private int telefonoContactoEmergencia;

    private Long idPaciente;
    private String nombrePaciente;
}
