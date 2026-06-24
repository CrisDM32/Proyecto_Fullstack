package com.hospital.gestion_de_pacientes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoEmergenciaResponse {

    private Long idContactoEmergencia;
    private String nombreContactoEmergencia;
    private int telefonoContactoEmergencia;

    private Long idPaciente;
    private String nombrePaciente;
}

