package com.hospital.gestion_de_citas.controller;

import com.hospital.gestion_de_citas.dto.request.AgendaMedicaRequest;
import com.hospital.gestion_de_citas.dto.response.AgendaMedicaResponse;
import com.hospital.gestion_de_citas.service.AgendaMedicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agendas")
@RequiredArgsConstructor
public class AgendaMedicaController {
    private final AgendaMedicaService agendaMedicaService;

    //GET OBTENER TODAS LAS AGENDAS
    @GetMapping
    public ResponseEntity<List<AgendaMedicaResponse>> obtenerTodo(){
        return ResponseEntity.ok().body(agendaMedicaService.listaAgendas());
    }

    //GET OBTENER AGENDA POR ID
    @GetMapping("/{id}")
    public ResponseEntity<AgendaMedicaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(agendaMedicaService.obtenerPorID(id));
    }

    //OBTENER POR ID PROFESIONAL
    @GetMapping("/profesionales/{id}")
    public ResponseEntity<List<AgendaMedicaResponse>> obtenerPorIdProfesional(@PathVariable Long id){
        return ResponseEntity.ok().body(agendaMedicaService.obtenerPorIdProfesional(id));
    }

    //POST CREAR AGENDA MEDICA
    @PostMapping
    public ResponseEntity<AgendaMedicaResponse> crearAgenda(@Valid @RequestBody AgendaMedicaRequest agendaMedicaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(agendaMedicaService.crear(agendaMedicaRequest));
    }

    //DELETE ELIMINAR AGENDA MEDICA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAgenda (@PathVariable Long id){
        agendaMedicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //PUT EDITAR AGENDA MEDICA
    @PutMapping("/{id}")
    public ResponseEntity<AgendaMedicaResponse> actualizarAgenda (@PathVariable Long id, @RequestBody AgendaMedicaRequest agendaMedicaRequest){
        return ResponseEntity.ok().body(agendaMedicaService.actualizar(id,agendaMedicaRequest));
    }
}
