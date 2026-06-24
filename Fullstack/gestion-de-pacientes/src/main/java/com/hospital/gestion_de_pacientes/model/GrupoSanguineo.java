package com.hospital.gestion_de_pacientes.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GrupoSanguineo {
    APositivo("A+"),
    OPositivo("O+"),
    BPositivo("B+"),
    ABPositivo("AB+"),
    ANegativo("A-"),
    ONegativo("O-"),
    BNegativo("B-"),
    ABNegativo("AB-");

    @JsonValue
    private final String descripcion;

    @JsonCreator
    public static GrupoSanguineo obtenerGrupo(String descripcion){
        for(GrupoSanguineo grupo : GrupoSanguineo.values()){
            if(grupo.descripcion.equalsIgnoreCase(descripcion)){
                return grupo;
            }
        }

        throw new IllegalArgumentException("Grupo sanguineo es inválido: " + descripcion);
    }
}