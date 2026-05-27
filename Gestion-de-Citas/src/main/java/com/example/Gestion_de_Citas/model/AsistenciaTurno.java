package com.example.Gestion_de_Citas.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AsistenciaTurno {
    Presente("PRESENTE"),
    Ausente("AUSENTE");

    @JsonValue
    private final String descripcion;

    @JsonCreator
    public static AsistenciaTurno obtenerVariable(String descripcion) {
        for (AsistenciaTurno estado : AsistenciaTurno.values()) {
            if (estado.descripcion.equalsIgnoreCase(descripcion)) {
                return estado;
            }
        }

        throw new IllegalArgumentException("La asistencia de turno es inválida: " + descripcion);
    }
}
