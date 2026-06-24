package com.hospital.gestion_de_pacientes.controller;

import com.hospital.gestion_de_pacientes.dto.request.ContactoEmergenciaRequest;
import com.hospital.gestion_de_pacientes.dto.response.ContactoEmergenciaResponse;
import com.hospital.gestion_de_pacientes.services.ContactoEmergenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contactos")
@RequiredArgsConstructor
public class ContactoEmergenciaController {

    private final ContactoEmergenciaService contactoEmergenciaService;


    @GetMapping
    public ResponseEntity<List<ContactoEmergenciaResponse>> obtenerContactos(){
        return ResponseEntity.ok().body(contactoEmergenciaService.obtenerContactos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoEmergenciaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(contactoEmergenciaService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<ContactoEmergenciaResponse> crearContacto(@Valid @RequestBody ContactoEmergenciaRequest contactoEmergenciaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(contactoEmergenciaService.crearContacto(contactoEmergenciaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContacto(@PathVariable Long id){
        contactoEmergenciaService.eliminarContacto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoEmergenciaResponse> actualizarContacto(@PathVariable Long id, @Valid @RequestBody ContactoEmergenciaRequest contactoEmergenciaRequest){
        return ResponseEntity.ok().body(contactoEmergenciaService.actualizarContacto(id,contactoEmergenciaRequest));
    }

}