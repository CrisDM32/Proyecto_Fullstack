package com.hospital.Gestion_de_Citas.dto.external;

import lombok.Data;

@Data
public class ContactoEmergenciaResponse {
    private Long idContactoEmergencia;
    private String nombreContactoEmergencia;
    private int telefonoContactoEmergencia;

    private Long idPaciente;
    private String nombrePaciente;
}
