package com.hospital.Pagos.controller;

import com.hospital.Pagos.dto.request.CitaRequest;
import com.hospital.Pagos.dto.response.CitaResponse;
import com.hospital.Pagos.services.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<CitaResponse>> obtenerTodo(){
        return ResponseEntity.ok().body(citaService.listarCitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(citaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CitaResponse> crear(@Valid @RequestBody CitaRequest citaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crear(citaRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaResponse> actualizarCita(@PathVariable Long id, @Valid @RequestBody CitaRequest citaRequest){
        return  ResponseEntity.ok().body(citaService.actualizar(id, citaRequest));
    }
}