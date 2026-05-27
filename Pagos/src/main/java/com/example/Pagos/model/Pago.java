package com.example.Pagos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @Column(name = "monto_pago", nullable = false)
    private int montoPago;

    @Column(name = "metodo_pago", length = 100, nullable = false)
    private String metodoPago;

    @Column(name = "id_transaccion_tarjeta")
    private int idTransaccionTarjeta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago",nullable = false)
    private EstadoPago estadoPago;

    @Column(name = "fecha_pago", nullable = false)
    private LocalDate fechaPago;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Cita cita;
}
