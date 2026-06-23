package com.hospital.gestion_de_pacientes.exception;

public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(Long id) {
        super("No se ha encontrado el paciente:" + id);
    }
}
