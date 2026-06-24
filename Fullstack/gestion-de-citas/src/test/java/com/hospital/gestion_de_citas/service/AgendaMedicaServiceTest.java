package com.hospital.gestion_de_citas.service;

import com.hospital.gestion_de_citas.dto.request.AgendaMedicaRequest;
import com.hospital.gestion_de_citas.dto.response.AgendaMedicaResponse;
import com.hospital.gestion_de_citas.exception.AgendaNoEncontradaException;
import com.hospital.gestion_de_citas.mapper.AgendaMedicaMapper;
import com.hospital.gestion_de_citas.model.AgendaMedica;
import com.hospital.gestion_de_citas.model.Profesional;
import com.hospital.gestion_de_citas.repository.AgendaMedicaRepository;
import com.hospital.gestion_de_citas.repository.CitaRepository;
import com.hospital.gestion_de_citas.repository.ProfesionalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaMedicaServiceTest {

    @Mock
    private AgendaMedicaRepository agendaMedicaRepository;

    @Mock
    private AgendaMedicaMapper agendaMedicaMapper;

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private ProfesionalRepository profesionalRepository;

    @InjectMocks
    private AgendaMedicaService agendaMedicaService;

    @Test
    void obtenerPorIdCorrecto() {
        // Given
        Long id = 1L;

        AgendaMedica agenda = crearAgenda(id);
        AgendaMedicaResponse response = crearResponse(id);

        when(agendaMedicaRepository.findById(id))
                .thenReturn(Optional.of(agenda));

        when(agendaMedicaMapper.toResponse(agenda))
                .thenReturn(response);

        // When
        AgendaMedicaResponse resultado =
                agendaMedicaService.obtenerPorID(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdAgenda());
    }

    @Test
    void obtenerPorIdIncorrecto() {
        // Given
        Long id = 99L;

        when(agendaMedicaRepository.findById(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                AgendaNoEncontradaException.class,
                () -> agendaMedicaService.obtenerPorID(id)
        );
    }

    @Test
    void crearAgendaCorrecto() {
        // Given
        Long idProfesional = 1L;

        AgendaMedicaRequest request = crearRequest();

        AgendaMedica agenda = crearAgenda(1L);
        Profesional profesional = crearProfesional(idProfesional);
        AgendaMedicaResponse response = crearResponse(1L);

        when(agendaMedicaMapper.toEntity(request))
                .thenReturn(agenda);

        when(profesionalRepository.findById(idProfesional))
                .thenReturn(Optional.of(profesional));

        when(agendaMedicaRepository.save(agenda))
                .thenReturn(agenda);

        when(agendaMedicaMapper.toResponse(agenda))
                .thenReturn(response);

        // When
        AgendaMedicaResponse resultado =
                agendaMedicaService.crear(request);

        // Then
        assertNotNull(resultado);

        verify(agendaMedicaRepository)
                .save(agenda);
    }

    @Test
    void crearAgendaIncorrecto() {
        // Given
        AgendaMedicaRequest request = crearRequest();

        when(agendaMedicaMapper.toEntity(request))
                .thenReturn(crearAgenda(1L));

        when(profesionalRepository.findById(request.getIdProfesional()))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> agendaMedicaService.crear(request)
        );

        verify(agendaMedicaRepository, never()).save(any());
    }

    @Test
    void actualizarAgendaCorrecto() {
        // Given
        Long idAgenda = 1L;
        Long idProfesional = 1L;

        AgendaMedica agendaExistente = crearAgenda(idAgenda);
        Profesional profesional = crearProfesional(idProfesional);

        AgendaMedicaRequest request = crearRequest();

        AgendaMedica agendaActualizada = crearAgenda(idAgenda);
        AgendaMedicaResponse response = crearResponse(idAgenda);

        when(agendaMedicaRepository.findById(idAgenda))
                .thenReturn(Optional.of(agendaExistente));

        when(profesionalRepository.findById(idProfesional))
                .thenReturn(Optional.of(profesional));

        when(agendaMedicaMapper.toEntity(request))
                .thenReturn(agendaActualizada);

        when(agendaMedicaRepository.save(any(AgendaMedica.class)))
                .thenReturn(agendaActualizada);

        when(agendaMedicaMapper.toResponse(agendaActualizada))
                .thenReturn(response);

        // When
        AgendaMedicaResponse resultado =
                agendaMedicaService.actualizar(idAgenda, request);

        // Then
        assertNotNull(resultado);
        assertEquals(idAgenda, resultado.getIdAgenda());
    }

    @Test
    void actualizarAgendaIncorrectoAgendaNoExiste() {
        // Given
        Long idAgenda = 99L;

        AgendaMedicaRequest request = crearRequest();

        when(agendaMedicaRepository.findById(idAgenda))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                AgendaNoEncontradaException.class,
                () -> agendaMedicaService.actualizar(idAgenda, request)
        );
    }

    @Test
    void actualizarAgendaIncorrectoProfesionalNoExiste() {
        // Given
        Long idAgenda = 1L;

        AgendaMedicaRequest request = crearRequest();

        when(agendaMedicaRepository.findById(idAgenda))
                .thenReturn(Optional.of(crearAgenda(idAgenda)));

        when(profesionalRepository.findById(request.getIdProfesional()))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> agendaMedicaService.actualizar(idAgenda, request)
        );

        verify(agendaMedicaRepository, never()).save(any());
    }

    private AgendaMedica crearAgenda(Long id) {
        AgendaMedica agenda = new AgendaMedica();
        agenda.setIdAgenda(id);
        agenda.setFecha(LocalDate.of(2026, 1, 1));
        agenda.setHoraInicioTurno(
                LocalDateTime.of(2026, 1, 1, 8, 0));
        agenda.setHoraFinTurno(
                LocalDateTime.of(2026, 1, 1, 18, 0));
        return agenda;
    }

    private AgendaMedicaResponse crearResponse(Long id) {
        AgendaMedicaResponse response = new AgendaMedicaResponse();
        response.setIdAgenda(id);
        return response;
    }

    private AgendaMedicaRequest crearRequest() {
        AgendaMedicaRequest request = new AgendaMedicaRequest();
        request.setIdProfesional(1L);
        return request;
    }

    private Profesional crearProfesional(Long id) {
        Profesional profesional = new Profesional();
        profesional.setIdProfesional(id);
        profesional.setNombreProfesional("Javier Rojas");
        return profesional;
    }
}