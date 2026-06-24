package com.hospital.pagos.service;

import com.hospital.pagos.dto.request.CitaRequest;
import com.hospital.pagos.dto.response.CitaResponse;
import com.hospital.pagos.exception.CitaNoEncontradaException;
import com.hospital.pagos.mapper.CitaMapper;
import com.hospital.pagos.model.Cita;
import com.hospital.pagos.repository.CitaRepository;
import com.hospital.pagos.services.CitaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaMapper citaMapper;

    @InjectMocks
    private CitaService citaService;

    @Test
    void buscarPorIdYEncuentra() {
        // Given
        Long id = 1L;
        Cita cita = crearCita(id);
        CitaResponse response = crearResponse(id);

        when(citaRepository.findById(id)).thenReturn(Optional.of(cita));
        when(citaMapper.toResponse(cita)).thenReturn(response);

        // When
        CitaResponse resultado = citaService.buscarPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdCita());
        assertEquals("Juan Pérez", resultado.getNombrePaciente());
    }

    @Test
    void buscarPorIdNoEncuentra() {
        // Given
        Long id = 99L;
        when(citaRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                CitaNoEncontradaException.class,
                () -> citaService.buscarPorId(id)
        );
    }

    @Test
    void crearCorrecto() {
        // Given
        CitaRequest request = new CitaRequest();
        request.setNombrePaciente("Juan Pérez");

        Cita cita = crearCita(1L);
        CitaResponse response = crearResponse(1L);

        when(citaMapper.toEntity(request)).thenReturn(cita);
        when(citaRepository.save(cita)).thenReturn(cita);
        when(citaMapper.toResponse(cita)).thenReturn(response);

        // When
        CitaResponse resultado = citaService.crear(request);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdCita());
        assertEquals("Juan Pérez", resultado.getNombrePaciente());

        verify(citaRepository).save(cita);
    }

    @Test
    void eliminarCorrecto() {
        // Given
        Long id = 1L;

        // When
        citaService.eliminar(id);

        // Then
        verify(citaRepository).deleteById(id);
    }

    @Test
    void actualizar_cuandoExiste_actualizaYRetornaCita() {
        // Given
        Long id = 1L;

        Cita citaExistente = crearCita(id);

        CitaRequest request = new CitaRequest();
        request.setNombrePaciente("María González");

        Cita citaActualizada = crearCita(id);
        citaActualizada.setNombrePaciente("María González");

        CitaResponse response = crearResponse(id);
        response.setNombrePaciente("María González");

        when(citaRepository.findById(id)).thenReturn(Optional.of(citaExistente));
        when(citaRepository.save(any(Cita.class))).thenReturn(citaActualizada);
        when(citaMapper.toResponse(citaActualizada)).thenReturn(response);

        // When
        CitaResponse resultado = citaService.actualizar(id, request);

        // Then
        assertNotNull(resultado);
        assertEquals("Maria Rojas", resultado.getNombrePaciente());

        verify(citaRepository).save(citaExistente);
    }

    @Test
    void actualizar_cuandoNoExiste_lanzaCitaNoEncontradaException() {
        // Given
        Long id = 99L;

        CitaRequest request = new CitaRequest();
        request.setNombrePaciente("Maria Rojas");

        when(citaRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                CitaNoEncontradaException.class,
                () -> citaService.actualizar(id, request)
        );

        verify(citaRepository, never()).save(any());
    }

    private Cita crearCita(Long id) {
        Cita cita = new Cita();
        cita.setIdCita(id);
        cita.setNombrePaciente("Javier Rojas");
        return cita;
    }

    private CitaResponse crearResponse(Long id) {
        CitaResponse response = new CitaResponse();
        response.setIdCita(id);
        response.setNombrePaciente("Javier Rojas");
        return response;
    }
}
