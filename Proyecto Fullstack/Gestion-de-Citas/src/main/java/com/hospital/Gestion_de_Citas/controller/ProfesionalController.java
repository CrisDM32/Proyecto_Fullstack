package com.hospital.Gestion_de_Citas.controller;

import com.hospital.Gestion_de_Citas.dto.request.ProfesionalRequest;
import com.hospital.Gestion_de_Citas.dto.response.ProfesionalResponse;
import com.hospital.Gestion_de_Citas.service.ProfesionalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profesionales")
@RequiredArgsConstructor
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    //GET OBTENER PROFESIONALES

    @GetMapping
    public ResponseEntity<List<ProfesionalResponse>> listarProfesional(){
        return ResponseEntity.ok().body(profesionalService.listaProfesionales());
    }

    //GET BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<ProfesionalResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(profesionalService.buscarPorId(id));
    }

    //POST CREAR PROFESIONAL
    @PostMapping
    public ResponseEntity<ProfesionalResponse> crearProfesional (@Valid @RequestBody ProfesionalRequest profesionalRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(profesionalService.crear(profesionalRequest));
    }

    //DELETE ELIMINAR PROFESIONAL
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProfesional(@PathVariable Long id){
        profesionalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //PUT ACTUALIZAR PROFESIONAL
    @PutMapping("/{id}")
    public ResponseEntity<ProfesionalResponse> actualizarProfesional (@Valid @PathVariable Long id, @RequestBody ProfesionalRequest profesionalRequest){
        return ResponseEntity.ok().body(profesionalService.actualizar(id,profesionalRequest));
    }
}