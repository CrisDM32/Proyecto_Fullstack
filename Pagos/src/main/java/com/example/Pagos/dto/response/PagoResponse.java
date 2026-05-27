package com.example.Pagos.dto.response;

import com.example.Pagos.model.EstadoPago;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PagoResponse {

    private Long idPago;
    private int montoPago;
    private String metodoPago;
    private int idTransaccionTarjeta;
    private EstadoPago estadoPago;
    private LocalDate fechaPago;

    private Long idCita;
}
