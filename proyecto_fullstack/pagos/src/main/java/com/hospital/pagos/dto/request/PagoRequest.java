package com.hospital.pagos.dto.request;

import com.hospital.pagos.model.EstadoPago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PagoRequest {

    @NotNull(message = "La id de cita es obligatoria")
    private Long idCita;

    @NotNull(message = "El monto de pago es obligatorio.")
    private int montoPago;

    @NotBlank(message = "El metodo de pago es obligatorio.")
    @Size(max = 100, message = "El metodo de pago no puede exceder los 100 carácteres.")
    private String metodoPago;

    private int idTransaccionTarjeta;

    @NotNull(message = "El estado del pago es obligatorio.")
    private EstadoPago estadoPago;

    @NotNull(message = "La fecha del pago es obligatoria.")
    private LocalDate fechaPago;
}