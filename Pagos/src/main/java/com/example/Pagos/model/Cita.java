package com.example.Pagos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cita")
public class Cita {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    @Column(name = "nombre_paciente", length = 100, nullable = false)
    private String nombrePaciente;

    @OneToMany(mappedBy = "cita")
    @JsonIgnore
    private List<Pago> pagos;
}
