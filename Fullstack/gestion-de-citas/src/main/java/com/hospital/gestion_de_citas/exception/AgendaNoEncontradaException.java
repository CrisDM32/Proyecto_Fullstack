package com.hospital.gestion_de_citas.exception;

public class AgendaNoEncontradaException extends RuntimeException {
    public AgendaNoEncontradaException(Long id) {
        super("No se ha encontrado la agenda medica con el id:" + id);
    }
}
