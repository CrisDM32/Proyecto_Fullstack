package com.example.Gestion_de_Citas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfesional;

    @Column(name = "rut",nullable = false)
    private int rut;

    @Column(name = "nombre_profesional",nullable = false,length = 150)
    private String nombreProfesional;

    @Column(name = "especialidad",nullable = false,length = 100)
    private String especialidad;

    @Column(name = "telefono",nullable = false)
    private int telefono;

    @Column(name = "correo",nullable = false,length = 100)
    private String correo;

    @Column(name = "direccion",nullable = false)
    private String direccion;

    @Column(name = "numero_licencia",nullable = false)
    private int numLicencia;

    @Column(name = "fecha_contratacion",nullable = false)
    private LocalDate fechaContratacion;

    @OneToMany(mappedBy = "profesional")
    @JsonIgnore
    private List<AgendaMedica> agendasMedicas;

}
