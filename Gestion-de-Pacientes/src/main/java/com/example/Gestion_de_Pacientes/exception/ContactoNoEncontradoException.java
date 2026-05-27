package com.example.Gestion_de_Pacientes.exception;

public class ContactoNoEncontradoException extends RuntimeException {
    public ContactoNoEncontradoException(Long id) {
        super("No se ha encontrado el contacto de emergencia con el id: "+ id);
    }
}
