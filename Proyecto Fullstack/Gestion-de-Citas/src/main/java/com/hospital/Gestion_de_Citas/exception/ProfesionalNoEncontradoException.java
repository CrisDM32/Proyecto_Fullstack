package com.hospital.Gestion_de_Citas.exception;

public class ProfesionalNoEncontradoException extends RuntimeException {
    public ProfesionalNoEncontradoException(Long id) {
        super("No se ha encontrado el profesional" + id);
    }
}
