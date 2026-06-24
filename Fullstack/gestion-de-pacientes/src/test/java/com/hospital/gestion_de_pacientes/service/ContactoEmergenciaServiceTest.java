package com.hospital.gestion_de_pacientes.service;

import com.hospital.gestion_de_pacientes.dto.request.ContactoEmergenciaRequest;
import com.hospital.gestion_de_pacientes.dto.response.ContactoEmergenciaResponse;
import com.hospital.gestion_de_pacientes.exception.ContactoNoEncontradoException;
import com.hospital.gestion_de_pacientes.mapper.ContactoEmergenciaMapper;
import com.hospital.gestion_de_pacientes.model.ContactoEmergencia;
import com.hospital.gestion_de_pacientes.model.Paciente;
import com.hospital.gestion_de_pacientes.repository.ContactoEmergenciaRepository;
import com.hospital.gestion_de_pacientes.repository.PacienteRepository;
import com.hospital.gestion_de_pacientes.services.ContactoEmergenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactoEmergenciaServiceTest {

    @Mock
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @Mock
    private ContactoEmergenciaMapper contactoEmergenciaMapper;

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private ContactoEmergenciaService contactoEmergenciaService;

    @Test
    void obtenerPorIdCorrecto() {
        // Given
        Long id = 1L;
        ContactoEmergencia contacto = crearContacto(id);
        ContactoEmergenciaResponse response = crearResponse(id);

        when(contactoEmergenciaRepository.findById(id))
                .thenReturn(Optional.of(contacto));

        when(contactoEmergenciaMapper.toResponse(contacto))
                .thenReturn(response);

        // When
        ContactoEmergenciaResponse resultado =
                contactoEmergenciaService.obtenerPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdContactoEmergencia());
        assertEquals("Javier Rojas", resultado.getNombreContactoEmergencia());
    }

    @Test
    void obtenerPorIdIncorrecto() {
        // Given
        Long id = 99L;

        when(contactoEmergenciaRepository.findById(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ContactoNoEncontradoException.class,
                () -> contactoEmergenciaService.obtenerPorId(id)
        );
    }

    @Test
    void crearContactoCorrecto() {
        // Given
        Long idPaciente = 1L;

        ContactoEmergenciaRequest request = crearRequest();
        request.setIdPaciente(idPaciente);

        ContactoEmergencia contacto = crearContacto(1L);
        Paciente paciente = crearPaciente(idPaciente);
        ContactoEmergenciaResponse response = crearResponse(1L);

        when(contactoEmergenciaMapper.toEntity(request))
                .thenReturn(contacto);

        when(pacienteRepository.findById(idPaciente))
                .thenReturn(Optional.of(paciente));

        when(contactoEmergenciaRepository.save(contacto))
                .thenReturn(contacto);

        when(contactoEmergenciaMapper.toResponse(contacto))
                .thenReturn(response);

        // When
        ContactoEmergenciaResponse resultado =
                contactoEmergenciaService.crearContacto(request);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdContactoEmergencia());

        verify(contactoEmergenciaRepository).save(contacto);
    }

    @Test
    void crearContactoIncorrecto() {
        // Given
        Long idPaciente = 99L;

        ContactoEmergenciaRequest request = crearRequest();
        request.setIdPaciente(idPaciente);

        ContactoEmergencia contacto = crearContacto(1L);

        when(contactoEmergenciaMapper.toEntity(request))
                .thenReturn(contacto);

        when(pacienteRepository.findById(idPaciente))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> contactoEmergenciaService.crearContacto(request)
        );

        verify(contactoEmergenciaRepository, never()).save(any());
    }

    @Test
    void eliminarContactoCorrecto() {
        // Given
        Long id = 1L;

        // When
        contactoEmergenciaService.eliminarContacto(id);

        // Then
        verify(contactoEmergenciaRepository).deleteById(id);
    }

    @Test
    void actualizarContactoCorrecto() {
        // Given
        Long idContacto = 1L;
        Long idPaciente = 2L;

        ContactoEmergencia contactoExistente = crearContacto(idContacto);
        Paciente paciente = crearPaciente(idPaciente);

        ContactoEmergenciaRequest request = crearRequest();
        request.setIdPaciente(idPaciente);
        request.setNombreContactoEmergencia("Maria Rojas");

        ContactoEmergencia contactoActualizado = crearContacto(idContacto);
        contactoActualizado.setNombreContactoEmergencia("Maria Rojas");
        contactoActualizado.setPaciente(paciente);

        ContactoEmergenciaResponse response = crearResponse(idContacto);
        response.setNombreContactoEmergencia("Maria Rojas");

        when(contactoEmergenciaRepository.findById(idContacto))
                .thenReturn(Optional.of(contactoExistente));

        when(pacienteRepository.findById(idPaciente))
                .thenReturn(Optional.of(paciente));

        when(contactoEmergenciaMapper.toEntity(request))
                .thenReturn(contactoActualizado);

        when(contactoEmergenciaRepository.save(any(ContactoEmergencia.class)))
                .thenReturn(contactoActualizado);

        when(contactoEmergenciaMapper.toResponse(contactoActualizado))
                .thenReturn(response);

        // When
        ContactoEmergenciaResponse resultado =
                contactoEmergenciaService.actualizarContacto(idContacto, request);

        // Then
        assertNotNull(resultado);
        assertEquals("Maria Rojas", resultado.getNombreContactoEmergencia());

        verify(contactoEmergenciaRepository)
                .save(any(ContactoEmergencia.class));
    }

    @Test
    void actualizarContactoIncorrectoContactoNoExiste() {
        // Given
        Long id = 99L;

        ContactoEmergenciaRequest request = crearRequest();

        when(contactoEmergenciaRepository.findById(id))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                ContactoNoEncontradoException.class,
                () -> contactoEmergenciaService.actualizarContacto(id, request)
        );

        verify(contactoEmergenciaRepository, never()).save(any());
    }

    @Test
    void actualizarContactoIncorrectoPacienteNoExiste() {
        // Given
        Long idContacto = 1L;
        Long idPaciente = 99L;

        ContactoEmergencia contactoExistente = crearContacto(idContacto);

        ContactoEmergenciaRequest request = crearRequest();
        request.setIdPaciente(idPaciente);

        when(contactoEmergenciaRepository.findById(idContacto))
                .thenReturn(Optional.of(contactoExistente));

        when(pacienteRepository.findById(idPaciente))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> contactoEmergenciaService.actualizarContacto(idContacto, request)
        );

        verify(contactoEmergenciaRepository, never()).save(any());
    }

    private ContactoEmergencia crearContacto(Long id) {
        ContactoEmergencia contacto = new ContactoEmergencia();
        contacto.setIdContactoEmergencia(id);
        contacto.setNombreContactoEmergencia("Javier Rojas");
        contacto.setTelefonoContactoEmergencia(987654321);
        return contacto;
    }

    private ContactoEmergenciaResponse crearResponse(Long id) {
        ContactoEmergenciaResponse response = new ContactoEmergenciaResponse();
        response.setIdContactoEmergencia(id);
        response.setNombreContactoEmergencia("Javier Rojas");
        response.setTelefonoContactoEmergencia(987654321);
        return response;
    }

    private ContactoEmergenciaRequest crearRequest() {
        ContactoEmergenciaRequest request = new ContactoEmergenciaRequest();
        request.setNombreContactoEmergencia("Javier Rojas");
        request.setTelefonoContactoEmergencia(987654321);
        return request;
    }

    private Paciente crearPaciente(Long id) {
        Paciente paciente = new Paciente();
        paciente.setIdPaciente(id);
        paciente.setNombrePaciente("Javier Rojas");
        return paciente;
    }
}