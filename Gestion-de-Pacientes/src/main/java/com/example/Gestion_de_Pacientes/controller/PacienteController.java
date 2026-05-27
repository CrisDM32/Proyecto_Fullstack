package com.example.Gestion_de_Pacientes.controller;

import com.example.Gestion_de_Pacientes.dto.request.PacienteRequest;
import com.example.Gestion_de_Pacientes.dto.response.PacienteResponse;
import com.example.Gestion_de_Pacientes.model.Paciente;
import com.example.Gestion_de_Pacientes.services.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@RequiredArgsConstructor

public class PacienteController {
    private final PacienteService pacienteService;

    //GET OBTENER PACIENTES

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarPaciente(){
        return ResponseEntity.ok().body(pacienteService.listaPacientes());
    }

    //GET OBTENER PACIENTE PR ID

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(pacienteService.buscarPorId(id));
    }

    //POST CREAR PACIENTE
    @PostMapping
    public ResponseEntity<PacienteResponse> crearPaciente(@Valid @RequestBody PacienteRequest pacienteRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.crear(pacienteRequest));
    }

    //DELETE ELIMINAR PACIENTE

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id){
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //PUT ACTUALIZAR CLIENTE

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> actualizarPaciente (@Valid @PathVariable Long id, @RequestBody PacienteRequest pacienteRequest){
        return ResponseEntity.ok().body(pacienteService.actualizar(id, pacienteRequest));
    }


}
