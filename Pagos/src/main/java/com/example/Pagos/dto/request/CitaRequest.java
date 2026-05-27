package com.example.Pagos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CitaRequest {


    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100, message = "El nombre no puede exceder los 100 carácteres.")
    private String nombrePaciente;
}
