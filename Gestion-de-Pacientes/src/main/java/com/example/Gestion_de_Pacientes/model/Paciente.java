package com.example.Gestion_de_Pacientes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaciente;

    @Column (name = "nombre_completo", nullable = false, length = 100)
    private String nombrePaciente;

    @Column (name = "rut", nullable = false)
    private int rut;

    @Column (name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column (name = "genero", nullable = false, length = 30)
    private String genero;

    @Column (name = "telefono", nullable = false)
    private Integer telefono;

    @Column (name = "correo", nullable = true, length = 75 )
    private String correo;

    @Column (name = "direccion", nullable = false)
    private String direccion;

    @Enumerated (EnumType.STRING)
    @Column(name = "grupo_sanguineo", nullable = false)
    private GrupoSanguineo grupoSanguineo;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private List<ContactoEmergencia> contactoEmergencia;

}
