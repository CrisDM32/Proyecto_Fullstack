package com.hospital.pagos.services;

import com.hospital.pagos.dto.request.CitaRequest;
import com.hospital.pagos.dto.response.CitaResponse;
import com.hospital.pagos.exception.CitaNoEncontradaException;
import com.hospital.pagos.mapper.CitaMapper;
import com.hospital.pagos.model.Cita;
import com.hospital.pagos.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;


    public List<CitaResponse> listarCitas(){
        return citaMapper.toResponseList(citaRepository.findAll());
    }

    public CitaResponse buscarPorId(Long id){
        return citaMapper.toResponse(citaRepository
                .findById(id)
                .orElseThrow(() -> new CitaNoEncontradaException(id)));
    }

    public CitaResponse crear(CitaRequest citaRequest) {
        return citaMapper.toResponse(citaRepository.save(citaMapper.toEntity(citaRequest)));
    }

    public void eliminar(Long id){
        citaRepository.deleteById(id);
    }

    public CitaResponse actualizar(Long id, CitaRequest citaRequest){
        Cita citaEncontrada = citaRepository
                .findById(id)
                .orElseThrow(() -> new CitaNoEncontradaException(id));

        citaEncontrada.setNombrePaciente(citaRequest.getNombrePaciente());

        return citaMapper.toResponse(citaRepository.save(citaEncontrada));


    }
}
