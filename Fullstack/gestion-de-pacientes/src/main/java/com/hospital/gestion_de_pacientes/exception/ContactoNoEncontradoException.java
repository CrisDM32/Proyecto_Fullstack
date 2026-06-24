package com.hospital.gestion_de_pacientes.exception;

public class ContactoNoEncontradoException extends RuntimeException {
    public ContactoNoEncontradoException(Long id) {
        super("No se ha encontrado el contacto de emergencia con el id: "+ id);
    }
}
