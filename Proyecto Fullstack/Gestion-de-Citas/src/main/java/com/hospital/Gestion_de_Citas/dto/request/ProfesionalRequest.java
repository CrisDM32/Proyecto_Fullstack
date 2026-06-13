package com.hospital.Gestion_de_Citas.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfesionalRequest {

    @NotNull(message = "El rut es obligatorio.")
    private int rut;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max=150, message = "El nombre no puede contener mas de 150 carácteres.")
    private String nombreProfesional;

    @NotBlank
    @Size(max = 100, message = "La especialidad no puede contener mas de 100 carácteres.")
    private String especialidad;

    @NotNull(message = "El mensaje no puede estar vacio.")
    private int telefono;

    @NotBlank (message = "El correo no pueden estar vacio.")
    @Size(max=100, message = "El correo no puede tener mas de 100 carácteres.")
    private String correo;

    @NotBlank (message = "La dirección no puede estar vacia.")
    @Size(max = 200, message = "La dirección no puede tener mas de 200 carácteres.")
    private String direccion;

    @NotNull (message = "El Número de licencia no puede estar vacio.")
    @Max(value = 999999, message = "El número de licencia debe ser en formato XXXXXX.")
    private int numLicencia;

    @NotNull(message = "La fecha de contratación es obligatoria.")
    private LocalDate fechaContratacion;

}
