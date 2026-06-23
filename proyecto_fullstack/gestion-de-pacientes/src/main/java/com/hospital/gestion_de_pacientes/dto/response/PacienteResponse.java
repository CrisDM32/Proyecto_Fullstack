package com.hospital.gestion_de_pacientes.dto.response;

import com.hospital.gestion_de_pacientes.model.ContactoEmergencia;
import com.hospital.gestion_de_pacientes.model.GrupoSanguineo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponse {

    private Long idPaciente;
    private String nombrePaciente;
    private int rut;
    private LocalDate fechaNacimiento;
    private String genero;
    private Integer telefono;
    private String correo;
    private String direccion;
    private GrupoSanguineo grupoSanguineo;

    private List<ContactoEmergencia> contactoEmergencia;
}