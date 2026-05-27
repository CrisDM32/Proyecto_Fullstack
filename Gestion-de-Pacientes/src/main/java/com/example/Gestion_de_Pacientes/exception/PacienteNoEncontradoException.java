package com.example.Gestion_de_Pacientes.exception;

public class PacienteNoEncontradoException extends RuntimeException {
    public PacienteNoEncontradoException(Long id) {
        super("No se ha encontrado el paciente:" + id);
    }
}
