package com.hospital.Gestion_de_Pacientes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "contactos_emergencia")
public class ContactoEmergencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContactoEmergencia;

    @Column(name = "nombre_contacto_emergencia", nullable = false, length = 100)
    private String nombreContactoEmergencia;

    @Column(name = "telefono_contacto_emergencia", nullable = false)
    private int telefonoContactoEmergencia;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    @JsonIgnore
    private Paciente paciente;
}