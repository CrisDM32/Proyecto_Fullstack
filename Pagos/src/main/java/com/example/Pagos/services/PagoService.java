package com.example.Pagos.services;

import com.example.Pagos.dto.request.PagoRequest;
import com.example.Pagos.dto.response.PagoResponse;
import com.example.Pagos.exception.PagoNoEncontradoException;
import com.example.Pagos.mapper.CitaMapper;
import com.example.Pagos.mapper.PagoMapper;
import com.example.Pagos.model.Cita;
import com.example.Pagos.model.Pago;
import com.example.Pagos.repository.CitaRepository;
import com.example.Pagos.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;
    private final CitaRepository citaRepository;

    public List<PagoResponse> listaPagos(){
        return pagoMapper.toResponseList(pagoRepository.findAll());
    }

    public PagoResponse obtenerPorId(Long id){
        return pagoMapper.toResponse(pagoRepository.findById(id).orElseThrow(()-> new PagoNoEncontradoException(id)));
    }

    public PagoResponse crear (PagoRequest pagoRequest){
        Pago pago = pagoMapper.toEntity(pagoRequest);
        Cita cita = citaRepository.findById(pagoRequest.getIdCita()).orElseThrow(()-> new RuntimeException("Cita no encontrada"));

        pago.setCita(cita);

        return pagoMapper.toResponse(pagoRepository.save(pago));
    }

    public void eliminar (Long id){
        pagoRepository.deleteById(id);
    }

    public PagoResponse actualizar(Long id, PagoRequest pagoRequest){
        Pago  pagoExistente = pagoRepository.findById(id).orElseThrow(()->new PagoNoEncontradoException(id));

        Cita cita = citaRepository.findById(pagoRequest.getIdCita()).orElseThrow(()->new RuntimeException("Cita no encontrada."));

        Pago pagoActualizado = pagoMapper.toEntity(pagoRequest);

        pagoActualizado.setIdPago(pagoExistente.getIdPago());
        pagoActualizado.setCita(cita);

        return  pagoMapper.toResponse(pagoRepository.save(pagoActualizado));
    }


}
