package com.example.Pagos.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoPago {
    Aprobado("APROBADO"),
    Procesando("PROCESANDO"),
    Rechazado("RECHAZADO");

    @JsonValue
    private final String descripcion;

    @JsonCreator
    public static EstadoPago obtenerVariable(String descripcion){
        for(EstadoPago estado : EstadoPago.values()){
            if(estado.descripcion.equalsIgnoreCase(descripcion)){
                return estado;
            }
        }

        throw new IllegalArgumentException("El estado del pago es inválido: " + descripcion);
    }
}
