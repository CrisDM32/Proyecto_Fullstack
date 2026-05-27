package com.example.Gestion_de_Pacientes.dto.request;

import com.example.Gestion_de_Pacientes.dto.response.ContactoEmergenciaResponse;
import com.example.Gestion_de_Pacientes.model.ContactoEmergencia;
import com.example.Gestion_de_Pacientes.model.GrupoSanguineo;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PacienteRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max=100, message = "El nombre debe tener un largo máximo de 100 carácteres.")
    private String nombrePaciente;

    @NotNull(message = "El rut es obligatorio")
    private int rut;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El género es obligatorio.")
    @Size(min=1,max = 30, message = "El género debe tener entre 1 y 30 carácteres.")
    private String genero;

    @NotNull(message = "El telefono es obligatorio.")
    private Integer telefono;

    @NotBlank(message = "El correo es obligatorio.")
    @Size(max=75,message = "El correo debe tener máximo 75 carácteres.")
    private String correo;

    @NotBlank(message = "La direccion es obligatoria.")
    @Size(max = 255, message = "La dirección no puede contener más de 255 carácteres.")
    private String direccion;

    @NotNull(message = "El Grupo Sanguineo es obligatorio.")
    private GrupoSanguineo grupoSanguineo;

    @NotNull(message = "El contacto de emergencia es obligatorio.")
    private List<ContactoEmergencia> contactoEmergencia;

}
