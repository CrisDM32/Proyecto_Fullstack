package com.example.Pagos.exception;

public class PagoNoEncontradoException extends RuntimeException {
    public PagoNoEncontradoException(Long id) {
        super("No se ha encontrado el pago con id: "+ id);
    }
}
