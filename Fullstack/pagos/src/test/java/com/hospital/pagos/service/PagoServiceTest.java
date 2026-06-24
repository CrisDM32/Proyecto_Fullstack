package com.hospital.pagos.service;

import com.hospital.pagos.dto.request.PagoRequest;
import com.hospital.pagos.dto.response.PagoResponse;
import com.hospital.pagos.exception.PagoNoEncontradoException;
import com.hospital.pagos.mapper.PagoMapper;
import com.hospital.pagos.model.Cita;
import com.hospital.pagos.model.Pago;
import com.hospital.pagos.repository.CitaRepository;
import com.hospital.pagos.repository.PagoRepository;
import com.hospital.pagos.services.PagoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private PagoMapper pagoMapper;

    @Mock
    private CitaRepository citaRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void obtenerPorIdYExiste() {
        // Given
        Long id = 1L;
        Pago pago = crearPago(id);
        PagoResponse response = crearResponse(id);

        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));
        when(pagoMapper.toResponse(pago)).thenReturn(response);

        // When
        PagoResponse resultado = pagoService.obtenerPorId(id);

        // Then
        assertNotNull(resultado);
        assertEquals(id, resultado.getIdPago());
    }

    @Test
    void obtenerPorIdYNoExiste() {
        // Given
        Long id = 99L;

        when(pagoRepository.findById(id)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                PagoNoEncontradoException.class,
                () -> pagoService.obtenerPorId(id)
        );
    }

    @Test
    void crearCorrecto() {
        // Given
        Long idCita = 1L;

        PagoRequest request = new PagoRequest();
        request.setIdCita(idCita);

        Pago pago = crearPago(1L);
        Cita cita = crearCita(idCita);
        PagoResponse response = crearResponse(1L);

        when(pagoMapper.toEntity(request)).thenReturn(pago);
        when(citaRepository.findById(idCita)).thenReturn(Optional.of(cita));
        when(pagoRepository.save(pago)).thenReturn(pago);
        when(pagoMapper.toResponse(pago)).thenReturn(response);

        // When
        PagoResponse resultado = pagoService.crear(request);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdPago());

        verify(citaRepository).findById(idCita);
        verify(pagoRepository).save(pago);
    }

    @Test
    void crearIncorrectoCitaNoExiste() {
        // Given
        Long idCita = 99L;

        PagoRequest request = new PagoRequest();
        request.setIdCita(idCita);

        Pago pago = crearPago(1L);

        when(pagoMapper.toEntity(request)).thenReturn(pago);
        when(citaRepository.findById(idCita)).thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> pagoService.crear(request)
        );

        verify(pagoRepository, never()).save(any());
    }

    @Test
    void eliminarCorrecto() {
        // Given
        Long id = 1L;

        // When
        pagoService.eliminar(id);

        // Then
        verify(pagoRepository).deleteById(id);
    }

    @Test
    void actualizarCorrecto() {
        // Given
        Long idPago = 1L;
        Long idCita = 2L;

        Pago pagoExistente = crearPago(idPago);
        Cita cita = crearCita(idCita);

        PagoRequest request = new PagoRequest();
        request.setIdCita(idCita);

        Pago pagoActualizado = crearPago(idPago);
        pagoActualizado.setCita(cita);

        PagoResponse response = crearResponse(idPago);

        when(pagoRepository.findById(idPago))
                .thenReturn(Optional.of(pagoExistente));

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.of(cita));

        when(pagoMapper.toEntity(request))
                .thenReturn(pagoActualizado);

        when(pagoRepository.save(any(Pago.class)))
                .thenReturn(pagoActualizado);

        when(pagoMapper.toResponse(pagoActualizado))
                .thenReturn(response);

        // When
        PagoResponse resultado = pagoService.actualizar(idPago, request);

        // Then
        assertNotNull(resultado);
        assertEquals(idPago, resultado.getIdPago());

        verify(pagoRepository).save(any(Pago.class));
    }

    @Test
    void actualizarIncorrectoSinPago() {
        // Given
        Long idPago = 99L;

        PagoRequest request = new PagoRequest();

        when(pagoRepository.findById(idPago))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                PagoNoEncontradoException.class,
                () -> pagoService.actualizar(idPago, request)
        );

        verify(pagoRepository, never()).save(any());
    }

    @Test
    void actualizarIncorrectoSinCita() {
        // Given
        Long idPago = 1L;
        Long idCita = 99L;

        Pago pagoExistente = crearPago(idPago);

        PagoRequest request = new PagoRequest();
        request.setIdCita(idCita);

        when(pagoRepository.findById(idPago))
                .thenReturn(Optional.of(pagoExistente));

        when(citaRepository.findById(idCita))
                .thenReturn(Optional.empty());

        // When / Then
        assertThrows(
                RuntimeException.class,
                () -> pagoService.actualizar(idPago, request)
        );

        verify(pagoRepository, never()).save(any());
    }

    private Pago crearPago(Long id) {
        Pago pago = new Pago();
        pago.setIdPago(id);
        return pago;
    }

    private PagoResponse crearResponse(Long id) {
        PagoResponse response = new PagoResponse();
        response.setIdPago(id);
        return response;
    }

    private Cita crearCita(Long id) {
        Cita cita = new Cita();
        cita.setIdCita(id);
        return cita;
    }
}