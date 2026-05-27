package com.example.Gestion_de_Citas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "agenda_medica")
public class AgendaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgenda;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio_turno", nullable = false)
    private LocalDateTime horaInicioTurno;

    @Column(name = "hora_fin_turno", nullable = false)
    private LocalDateTime horaFinTurno;

    @Enumerated(EnumType.STRING)
    @Column(name = "asistencia_turno", nullable = false)
    private AsistenciaTurno asistenciaTurno;

    @OneToMany(mappedBy = "agenda")
    private List<Cita> citas;

    @ManyToOne
    @JoinColumn(name = "id_profesional")
    private Profesional profesional;

}
