package com.hospital.pagos.dto.response;

import com.hospital.pagos.model.EstadoPago;
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
