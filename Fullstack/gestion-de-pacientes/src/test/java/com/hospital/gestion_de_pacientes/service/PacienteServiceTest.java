package com.hospital.gestion_de_pacientes.service;

import com.hospital.gestion_de_pacientes.dto.request.PacienteRequest;
import com.hospital.gestion_de_pacientes.dto.response.PacienteResponse;
import com.hospital.gestion_de_pacientes.exception.PacienteNoEncontradoException;
import com.hospital.gestion_de_pacientes.mapper.PacienteMapper;
import com.hospital.gestion_de_pacientes.model.Paciente;
import com.hospital.gestion_de_pacientes.repository.PacienteRepository;
import com.hospital.gestion_de_pacientes.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private PacienteMapper pacienteMapper;

    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void buscarPorIdCorrecto() {
        // Given
        Long id = 1L;
        Paciente paciente = crearPaciente(id);
        PacienteResponse response = crearResponse(id);

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(paciente));
        when(pacienteMapper.toResponse(paciente)).thenReturn(response);

        // When
        PacienteResponse resultado = pacienteService.buscarPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdPaciente());
        assertEquals("Javier Rojas", resultado.getNombrePaciente());
    }

    @Test
    void buscarPorIdIncorrecto() {
        // Given
        Long id = 99L;

        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                PacienteNoEncontradoException.class,
                () -> pacienteService.buscarPorId(id)
        );
    }

    @Test
    void crearCorrecto() {
        // Given
        PacienteRequest request = crearRequest();

        Paciente paciente = crearPaciente(1L);
        PacienteResponse response = crearResponse(1L);

        when(pacienteRepository.existsByRut(request.getRut())).thenReturn(false);
        when(pacienteMapper.toEntity(request)).thenReturn(paciente);
        when(pacienteRepository.save(paciente)).thenReturn(paciente);
        when(pacienteMapper.toResponse(paciente)).thenReturn(response);

        // When
        PacienteResponse resultado = pacienteService.crear(request);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPaciente());
        assertEquals("Javier Rojas", resultado.getNombrePaciente());

        verify(pacienteRepository).save(paciente);
    }

    @Test
    void crearIncorrectoRutExistente() {
        // Given
        PacienteRequest request = crearRequest();

        when(pacienteRepository.existsByRut(request.getRut())).thenReturn(true);

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> pacienteService.crear(request)
        );

        verify(pacienteRepository, never()).save(any());
    }

    @Test
    void eliminarCorrecto() {
        // Given
        Long id = 1L;

        // When
        pacienteService.eliminar(id);

        // Then
        verify(pacienteRepository).deleteById(id);
    }

    @Test
    void actualizarCorrecto() {
        // Given
        Long id = 1L;

        Paciente paciente = crearPaciente(id);

        PacienteRequest request = crearRequest();
        request.setNombrePaciente("María Rojas");

        Paciente pacienteActualizado = crearPaciente(id);
        pacienteActualizado.setNombrePaciente("María Rojas");

        PacienteResponse response = crearResponse(id);
        response.setNombrePaciente("María Rojas");

        when(pacienteRepository.findById(id))
                .thenReturn(Optional.of(paciente));

        when(pacienteRepository.save(any(Paciente.class)))
                .thenReturn(pacienteActualizado);

        when(pacienteMapper.toResponse(pacienteActualizado))
                .thenReturn(response);

        // When
        PacienteResponse resultado = pacienteService.actualizar(id, request);

        // Then
        assertNotNull(resultado);
        assertEquals("María Rojas", resultado.getNombrePaciente());

        verify(pacienteRepository).save(paciente);
    }

    @Test
    void actualizarIncorrecto() {
        // Given
        Long id = 99L;

        PacienteRequest request = crearRequest();

        when(pacienteRepository.findById(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                PacienteNoEncontradoException.class,
                () -> pacienteService.actualizar(id, request)
        );

        verify(pacienteRepository, never()).save(any());
    }

    private Paciente crearPaciente(Long id) {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(id);
        paciente.setNombrePaciente("Javier Rojas");
        paciente.setRut(11111111-1);
        paciente.setTelefono(987654321);
        paciente.setCorreo("javier@test.cl");
        return paciente;
    }

    private PacienteResponse crearResponse(Long id) {
        PacienteResponse response = new PacienteResponse();
        response.setIdPaciente(id);
        response.setNombrePaciente("Javier Rojas");
        response.setRut(11111111-1);
        return response;
    }

    private PacienteRequest crearRequest() {
        PacienteRequest request = new PacienteRequest();
        request.setNombrePaciente("Javier Rojas");
        request.setRut(11111111-1);
        request.setTelefono(987654321);
        request.setCorreo("javier@test.cl");
        return request;
    }
}