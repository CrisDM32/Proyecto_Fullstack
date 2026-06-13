package com.hospital.Pagos.exception;

public class CitaNoEncontradaException extends RuntimeException {
    public CitaNoEncontradaException(Long id) {
        super("No fue encontrada la Cita con el id:" + id);
    }
}
