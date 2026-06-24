package com.hospital.gestion_de_citas.service;

import com.hospital.gestion_de_citas.client.PacientesClient;
import com.hospital.gestion_de_citas.dto.external.PacienteResponse;
import com.hospital.gestion_de_citas.dto.request.CitaRequest;
import com.hospital.gestion_de_citas.dto.response.CitaConPacienteResponse;
import com.hospital.gestion_de_citas.dto.response.CitaResponse;
import com.hospital.gestion_de_citas.exception.AgendaNoEncontradaException;
import com.hospital.gestion_de_citas.exception.CitaNoEncontradaException;
import com.hospital.gestion_de_citas.exception.ProfesionalNoEncontradoException;
import com.hospital.gestion_de_citas.mapper.CitaMapper;
import com.hospital.gestion_de_citas.model.AgendaMedica;
import com.hospital.gestion_de_citas.model.Cita;
import com.hospital.gestion_de_citas.model.EstadoCita;
import com.hospital.gestion_de_citas.model.Profesional;
import com.hospital.gestion_de_citas.repository.AgendaMedicaRepository;
import com.hospital.gestion_de_citas.repository.CitaRepository;
import com.hospital.gestion_de_citas.repository.ProfesionalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private CitaMapper citaMapper;

    @Mock
    private AgendaMedicaRepository agendaMedicaRepository;

    @Mock
    private ProfesionalRepository profesionalRepository;

    @Mock
    private PacientesClient pacientesClient;

    @InjectMocks
    private CitaService citaService;

    @Test
    void obtenerPorIdCitaCorrecto() {
        // Given
        Long idCita = 1L;

        Cita cita = crearCita(idCita);
        CitaResponse response = crearResponse(idCita);

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.of(cita));

        when(citaMapper.toResponse(cita))
                .thenReturn(response);

        PacienteResponse pacienteResponse = crearPacienteResponse();

        when(pacientesClient.getPaciente(response.getIdPaciente()))
                .thenReturn(pacienteResponse);

        // When
        CitaConPacienteResponse resultado =
                citaService.buscarPorIdCita(idCita);

        // Then
        assertNotNull(resultado);
        assertEquals(idCita, resultado.getIdCita());
    }

    @Test
    void obtenerPorIdCitaIncorrecto() {
        // Given
        Long idCita = 99L;

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                CitaNoEncontradaException.class,
                () -> citaService.buscarPorIdCita(idCita)
        );
    }

    @Test
    void crearCitaCorrecto() {
        // Given
        Long idAgenda = 1L;
        Long idProfesional = 1L;

        AgendaMedica agenda = crearAgenda(idAgenda);
        Profesional profesional = crearProfesional(idProfesional);

        CitaRequest request = crearRequest();

        Cita citaGuardada = crearCita(1L);
        CitaResponse response = crearResponse(1L);

        when(agendaMedicaRepository.findById(idAgenda))
                .thenReturn(Optional.of(agenda));

        when(profesionalRepository.findById(idProfesional))
                .thenReturn(Optional.of(profesional));

        when(citaRepository.existsByFechaHoraCitaAndIdProfesional(
                request.getFechaHoraCita(),
                idProfesional))
                .thenReturn(false);

        when(citaRepository.save(any(Cita.class)))
                .thenReturn(citaGuardada);

        when(citaMapper.toResponse(citaGuardada))
                .thenReturn(response);

        // When
        CitaResponse resultado =
                citaService.crearCita(request);

        // Then
        assertNotNull(resultado);

        verify(citaRepository)
                .save(any(Cita.class));
    }

    @Test
    void crearCitaIncorrectoAgendaNoExiste() {
        // Given
        CitaRequest request = crearRequest();

        when(agendaMedicaRepository.findById(request.getIdAgenda()))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                AgendaNoEncontradaException.class,
                () -> citaService.crearCita(request)
        );
    }

    @Test
    void crearCitaIncorrectoProfesionalNoExiste() {
        // Given
        CitaRequest request = crearRequest();

        when(agendaMedicaRepository.findById(request.getIdAgenda()))
                .thenReturn(Optional.of(crearAgenda(request.getIdAgenda())));

        when(profesionalRepository.findById(request.getIdProfesional()))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ProfesionalNoEncontradoException.class,
                () -> citaService.crearCita(request)
        );
    }

    @Test
    void crearCitaIncorrectoHorarioFueraDeAgenda() {
        // Given
        CitaRequest request = crearRequest();

        AgendaMedica agenda = crearAgenda(request.getIdAgenda());

        agenda.setHoraInicioTurno(
                LocalDateTime.of(2026, 1, 1, 10, 0));

        agenda.setHoraFinTurno(
                LocalDateTime.of(2026, 1, 1, 11, 0));

        request.setFechaHoraCita(
                LocalDateTime.of(2026, 1, 1, 12, 0));

        when(agendaMedicaRepository.findById(request.getIdAgenda()))
                .thenReturn(Optional.of(agenda));

        when(profesionalRepository.findById(request.getIdProfesional()))
                .thenReturn(Optional.of(
                        crearProfesional(request.getIdProfesional())
                ));

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> citaService.crearCita(request)
        );
    }

    @Test
    void crearCitaIncorrectoHorarioOcupado() {
        // Given
        CitaRequest request = crearRequest();

        when(agendaMedicaRepository.findById(request.getIdAgenda()))
                .thenReturn(Optional.of(
                        crearAgenda(request.getIdAgenda())
                ));

        when(profesionalRepository.findById(request.getIdProfesional()))
                .thenReturn(Optional.of(
                        crearProfesional(request.getIdProfesional())
                ));

        when(citaRepository.existsByFechaHoraCitaAndIdProfesional(
                request.getFechaHoraCita(),
                request.getIdProfesional()))
                .thenReturn(true);

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> citaService.crearCita(request)
        );
    }

    @Test
    void cancelarCitaCorrecto() {
        // Given
        Long idCita = 1L;

        Cita cita = crearCita(idCita);

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.of(cita));

        when(citaRepository.save(cita))
                .thenReturn(cita);

        when(citaMapper.toResponse(cita))
                .thenReturn(crearResponse(idCita));

        // When
        CitaResponse resultado =
                citaService.cancelarCita(idCita);

        // Then
        assertNotNull(resultado);
        assertEquals(
                EstadoCita.CANCELADA,
                cita.getEstadoCita()
        );
    }

    @Test
    void cancelarCitaIncorrecto() {
        // Given
        Long idCita = 99L;

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                CitaNoEncontradaException.class,
                () -> citaService.cancelarCita(idCita)
        );
    }

    @Test
    void actualizarCitaCorrecto() {
        // Given
        Long idCita = 1L;

        Cita cita = crearCita(idCita);

        CitaRequest request = crearRequest();
        request.setMotivoConsulta("Control anual");

        CitaResponse response = crearResponse(idCita);
        response.setMotivoConsulta("Control anual");

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.of(cita));

        when(citaRepository.save(any(Cita.class)))
                .thenReturn(cita);

        when(citaMapper.toResponse(any(Cita.class)))
                .thenReturn(response);

        // When
        CitaResponse resultado =
                citaService.actualizarCita(idCita, request);

        // Then
        assertNotNull(resultado);
        assertEquals(
                "Control anual",
                resultado.getMotivoConsulta()
        );
    }

    @Test
    void actualizarCitaIncorrecto() {
        // Given
        Long idCita = 99L;

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                CitaNoEncontradaException.class,
                () -> citaService.actualizarCita(idCita, crearRequest())
        );
    }

    private Profesional crearProfesional(Long id) {
        Profesional profesional = new Profesional();
        profesional.setIdProfesional(id);
        profesional.setNombreProfesional("Javier Rojas");
        return profesional;
    }

    private AgendaMedica crearAgenda(Long id) {
        AgendaMedica agenda = new AgendaMedica();
        agenda.setIdAgenda(id);
        agenda.setHoraInicioTurno(
                LocalDateTime.of(2026, 1, 1, 8, 0));
        agenda.setHoraFinTurno(
                LocalDateTime.of(2026, 1, 1, 18, 0));
        return agenda;
    }

    private Cita crearCita(Long id) {
        Cita cita = new Cita();
        cita.setIdCita(id);
        cita.setIdPaciente(1L);
        cita.setIdProfesional(1L);
        cita.setIdPago(1L);
        cita.setMotivoConsulta("Consulta general");
        cita.setEstadoCita(EstadoCita.PROGRAMADA);
        cita.setFechaHoraCita(
                LocalDateTime.of(2026, 1, 1, 10, 0));
        return cita;
    }

    private CitaResponse crearResponse(Long id) {
        CitaResponse response = new CitaResponse();
        response.setIdCita(id);
        response.setIdPaciente(1L);
        response.setIdProfesional(1L);
        response.setIdPago(1L);
        response.setIdAgenda(1L);
        response.setMotivoConsulta("Consulta general");
        response.setEstadoCita(EstadoCita.PROGRAMADA);
        response.setFechaHoraCita(
                LocalDateTime.of(2026, 1, 1, 10, 0));
        return response;
    }

    private CitaRequest crearRequest() {
        CitaRequest request = new CitaRequest();
        request.setIdPaciente(1L);
        request.setIdProfesional(1L);
        request.setIdAgenda(1L);
        request.setIdPago(1L);
        request.setMotivoConsulta("Consulta general");
        request.setEstadoCita(EstadoCita.PROGRAMADA);
        request.setFechaHoraCita(
                LocalDateTime.of(2026, 1, 1, 10, 0));
        return request;
    }

    private PacienteResponse crearPacienteResponse() {
        PacienteResponse paciente = new PacienteResponse();
        paciente.setIdPaciente(1L);
        paciente.setNombrePaciente("Javier Rojas");
        return paciente;
    }
}