package com.hospital.pagos.services;

import com.hospital.pagos.dto.request.CitaRequest;
import com.hospital.pagos.dto.response.CitaResponse;
import com.hospital.pagos.exception.CitaNoEncontradaException;
import com.hospital.pagos.mapper.CitaMapper;
import com.hospital.pagos.model.Cita;
import com.hospital.pagos.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private static final Logger log = LoggerFactory.getLogger(CitaService.class);


    public List<CitaResponse> listarCitas(){
        return citaMapper.toResponseList(citaRepository.findAll());
    }

    public CitaResponse buscarPorId(Long id){
        log.info("Se está obteniendo una cita con el id {}", id);
        return citaMapper.toResponse(citaRepository
                .findById(id)
                .orElseThrow(() -> new CitaNoEncontradaException(id)));
    }

    public CitaResponse crear(CitaRequest citaRequest) {
        log.info("Datos de la cita al momento de crear {}", citaRequest);
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

        log.info("Datos de la cita al momento de actualizar {}", citaRequest);
        return citaMapper.toResponse(citaRepository.save(citaEncontrada));


    }
}
