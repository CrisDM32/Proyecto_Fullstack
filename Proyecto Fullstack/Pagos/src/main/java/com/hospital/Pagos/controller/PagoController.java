package com.hospital.Pagos.controller;

import com.hospital.Pagos.dto.request.PagoRequest;
import com.hospital.Pagos.dto.response.PagoResponse;
import com.hospital.Pagos.services.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoResponse>> obtenerTodo(){
        return ResponseEntity.ok().body(pagoService.listaPagos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PagoResponse> obtenerPorId (@PathVariable Long id){
        return ResponseEntity.ok().body(pagoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<PagoResponse> crearPago(@Valid @RequestBody PagoRequest pagoRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(pagoService.crear(pagoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoResponse> actualizarPago (@PathVariable Long id, @Valid @RequestBody PagoRequest pagoRequest){
        return ResponseEntity.ok().body(pagoService.actualizar(id, pagoRequest));
    }
}