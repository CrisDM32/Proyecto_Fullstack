package com.hospital.gestion_de_citas.exception;

public class ProfesionalNoEncontradoException extends RuntimeException {
    public ProfesionalNoEncontradoException(Long id) {
        super("No se ha encontrado el profesional" + id);
    }
}
