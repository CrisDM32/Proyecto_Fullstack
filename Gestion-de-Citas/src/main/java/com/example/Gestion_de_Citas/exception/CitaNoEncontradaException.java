package com.example.Gestion_de_Citas.exception;

public class CitaNoEncontradaException extends RuntimeException {
    public CitaNoEncontradaException(Long id) {
        super("No se ha encontrado la cita con el id: "+ id);
    }
}
