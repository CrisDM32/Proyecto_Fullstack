package com.example.Pagos.services;

import com.example.Pagos.dto.request.CitaRequest;
import com.example.Pagos.dto.response.CitaResponse;
import com.example.Pagos.exception.CitaNoEncontradaException;
import com.example.Pagos.mapper.CitaMapper;
import com.example.Pagos.model.Cita;
import com.example.Pagos.repository.CitaRepository;
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
