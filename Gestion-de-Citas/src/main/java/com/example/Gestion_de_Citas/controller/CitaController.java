package com.example.Gestion_de_Citas.controller;

import com.example.Gestion_de_Citas.dto.request.CitaRequest;
import com.example.Gestion_de_Citas.dto.response.CitaConPacienteResponse;
import com.example.Gestion_de_Citas.dto.response.CitaResponse;
import com.example.Gestion_de_Citas.service.CitaService;
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
    public final CitaService citaService;

    //obtener todas las citas
    @GetMapping
    public ResponseEntity<List<CitaResponse>> listar(){
        return ResponseEntity.ok().body(citaService.listarCitas());
    }
    //obtener por id cita

    @GetMapping("/{idCita}")
    public ResponseEntity<CitaConPacienteResponse> buscarPorIdCita(@PathVariable Long idCita){
        return ResponseEntity.ok().body(citaService.buscarPorIdCita(idCita));
    }
    //obtener por id paciente

    @GetMapping("/pacientes/{idPaciente}")
    public ResponseEntity<List<CitaResponse>> buscarPorIdPaciente(@PathVariable Long idPaciente){
        return ResponseEntity.ok().body(citaService.buscarPorPaciente(idPaciente));
    }
    //obtener por id profesional

    @GetMapping("/profesionales/{idProfesional}")
    public ResponseEntity<List<CitaResponse>> buscarPorIdProfesional (@PathVariable Long idProfesional){
        return ResponseEntity.ok().body(citaService.buscarPorProfesional(idProfesional));
    }

    //obtener por id agenda

    @GetMapping("/agendas/{idAgenda}")
    public ResponseEntity<List<CitaResponse>> buscarPorIdAgenda(@PathVariable Long idAgenda){
        return ResponseEntity.ok().body(citaService.buscarPorAgenda(idAgenda));
    }

    //obtener por id pago

    @GetMapping("/pagos/{idPago}")
    public ResponseEntity<List<CitaResponse>> buscarPorIdPago(@PathVariable Long idPago){
        return ResponseEntity.ok().body(citaService.buscarPorPago(idPago));
    }
    //crear cita

    @PostMapping
    public ResponseEntity<CitaResponse> crearCita(@Valid @RequestBody CitaRequest citaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.crearCita(citaRequest));
    }

    //cancelar cita
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<CitaResponse> cancelarCita(@PathVariable Long id){
        return  ResponseEntity.ok().body(citaService.cancelarCita(id));
    }

    //eliminar cita

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id){
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }

    //editar cita
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CitaResponse> actualizarCita(@PathVariable Long id, @Valid @RequestBody CitaRequest citaRequest){
        return ResponseEntity.ok().body(citaService.actualizarCita(id,citaRequest));
    }
}
