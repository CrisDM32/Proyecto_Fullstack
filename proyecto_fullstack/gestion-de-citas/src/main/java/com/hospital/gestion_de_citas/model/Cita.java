package com.hospital.gestion_de_citas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(name = "id_paciente", nullable = false)
    private Long idPaciente;

    @Column(name = "id_profesional", nullable = false)
    private Long idProfesional;

    @Column(name = "id_pago", nullable = false)
    private Long idPago;

    @Column(name = "fecha_hora_cita", nullable = false)
    private LocalDateTime fechaHoraCita;

    @Column(name = "motivo_consulta", length = 200, nullable = false)
    private String motivoConsulta;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_cita", nullable = false)
    private EstadoCita estadoCita;

    @ManyToOne
    @JoinColumn(name = "id_agenda")
    private AgendaMedica agenda;

}