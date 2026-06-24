package com.hospital.gestion_de_citas.service;

import com.hospital.gestion_de_citas.dto.request.ProfesionalRequest;
import com.hospital.gestion_de_citas.dto.response.ProfesionalResponse;
import com.hospital.gestion_de_citas.exception.ProfesionalNoEncontradoException;
import com.hospital.gestion_de_citas.mapper.ProfesionalMapper;
import com.hospital.gestion_de_citas.model.Profesional;
import com.hospital.gestion_de_citas.repository.ProfesionalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfesionalServiceTest {

    @Mock
    private ProfesionalRepository profesionalRepository;

    @Mock
    private ProfesionalMapper profesionalMapper;

    @InjectMocks
    private ProfesionalService profesionalService;

    @Test
    void obtenerPorIdCorrecto() {
        // Given
        Long id = 1L;
        Profesional profesional = crearProfesional(id);
        ProfesionalResponse response = crearResponse(id);

        when(profesionalRepository.findById(id))
                .thenReturn(Optional.of(profesional));

        when(profesionalMapper.toResponse(profesional))
                .thenReturn(response);

        // When
        ProfesionalResponse resultado =
                profesionalService.buscarPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdProfesional());
        assertEquals("Javier Rojas", resultado.getNombreProfesional());
    }

    @Test
    void obtenerPorIdIncorrecto() {
        // Given
        Long id = 99L;

        when(profesionalRepository.findById(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ProfesionalNoEncontradoException.class,
                () -> profesionalService.buscarPorId(id)
        );
    }

    @Test
    void crearProfesionalCorrecto() {
        // Given
        ProfesionalRequest request = crearRequest();

        Profesional profesional = crearProfesional(1L);
        ProfesionalResponse response = crearResponse(1L);

        when(profesionalRepository.existsByRut(request.getRut()))
                .thenReturn(false);

        when(profesionalRepository.existsByNumLicencia(request.getNumLicencia()))
                .thenReturn(false);

        when(profesionalMapper.toEntity(request))
                .thenReturn(profesional);

        when(profesionalRepository.save(profesional))
                .thenReturn(profesional);

        when(profesionalMapper.toResponse(profesional))
                .thenReturn(response);

        // When
        ProfesionalResponse resultado =
                profesionalService.crear(request);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProfesional());

        verify(profesionalRepository).save(profesional);
    }

    @Test
    void crearProfesionalIncorrectoRutExiste() {
        // Given
        ProfesionalRequest request = crearRequest();

        when(profesionalRepository.existsByRut(request.getRut()))
                .thenReturn(true);

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> profesionalService.crear(request)
        );

        verify(profesionalRepository, never()).save(any());
    }

    @Test
    void crearProfesionalIncorrectoLicenciaExiste() {
        // Given
        ProfesionalRequest request = crearRequest();

        when(profesionalRepository.existsByRut(request.getRut()))
                .thenReturn(false);

        when(profesionalRepository.existsByNumLicencia(request.getNumLicencia()))
                .thenReturn(true);

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> profesionalService.crear(request)
        );

        verify(profesionalRepository, never()).save(any());
    }

    @Test
    void eliminarProfesionalCorrecto() {
        // Given
        Long id = 1L;

        // When
        profesionalService.eliminar(id);

        // Then
        verify(profesionalRepository).deleteById(id);
    }

    @Test
    void actualizarProfesionalCorrecto() {
        // Given
        Long id = 1L;

        Profesional profesionalExistente = crearProfesional(id);

        ProfesionalRequest request = crearRequest();
        request.setNombreProfesional("Maria Rojas");

        Profesional profesionalActualizado = crearProfesional(id);
        profesionalActualizado.setNombreProfesional("Maria Rojas");

        ProfesionalResponse response = crearResponse(id);
        response.setNombreProfesional("Maria Rojas");

        when(profesionalRepository.findById(id))
                .thenReturn(Optional.of(profesionalExistente));

        when(profesionalRepository.save(any(Profesional.class)))
                .thenReturn(profesionalActualizado);

        when(profesionalMapper.toResponse(profesionalActualizado))
                .thenReturn(response);

        // When
        ProfesionalResponse resultado =
                profesionalService.actualizar(id, request);

        // Then
        assertNotNull(resultado);
        assertEquals("Maria Rojas", resultado.getNombreProfesional());

        verify(profesionalRepository).save(profesionalExistente);
    }

    @Test
    void actualizarProfesionalIncorrecto() {
        // Given
        Long id = 99L;

        ProfesionalRequest request = crearRequest();

        when(profesionalRepository.findById(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ProfesionalNoEncontradoException.class,
                () -> profesionalService.actualizar(id, request)
        );

        verify(profesionalRepository, never()).save(any());
    }

    private Profesional crearProfesional(Long id) {
        Profesional profesional = new Profesional();
        profesional.setIdProfesional(id);
        profesional.setRut(11111111-1);
        profesional.setNombreProfesional("Javier Rojas");
        profesional.setEspecialidad("Cardiología");
        profesional.setTelefono(987654321);
        profesional.setCorreo("javier@hospital.cl");
        profesional.setDireccion("Santiago");
        profesional.setNumLicencia(123456);
        return profesional;
    }

    private ProfesionalResponse crearResponse(Long id) {
        ProfesionalResponse response = new ProfesionalResponse();
        response.setIdProfesional(id);
        response.setRut(11111111-1);
        response.setNombreProfesional("Javier Rojas");
        response.setEspecialidad("Cardiología");
        response.setNumLicencia(123456);
        return response;
    }

    private ProfesionalRequest crearRequest() {
        ProfesionalRequest request = new ProfesionalRequest();
        request.setRut(11111111-1);
        request.setNombreProfesional("Javier Rojas");
        request.setEspecialidad("Cardiología");
        request.setTelefono(987654321);
        request.setCorreo("javier@hospital.cl");
        request.setDireccion("Santiago");
        request.setNumLicencia(123456);
        return request;
    }
}