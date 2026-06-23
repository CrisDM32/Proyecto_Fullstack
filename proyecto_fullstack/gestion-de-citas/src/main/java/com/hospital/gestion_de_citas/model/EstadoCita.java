package com.hospital.gestion_de_citas.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoCita {
    PROGRAMADA("Programada"),
    CANCELADA("Cancelada"),
    COMPLETADA("Completada");

    @JsonValue
    private final String descripcion;

    @JsonCreator
    public static EstadoCita obtenerVariable(String descripcion){
        for(EstadoCita estado : EstadoCita.values()){
            if(estado.descripcion.equalsIgnoreCase(descripcion)){
                return estado;
            }
        }

        throw new IllegalArgumentException("El estado de la cita es inválido: " + descripcion);
    }

}
