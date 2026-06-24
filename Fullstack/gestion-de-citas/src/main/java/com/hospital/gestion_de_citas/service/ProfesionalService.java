package com.hospital.gestion_de_citas.service;

import com.hospital.gestion_de_citas.dto.request.ProfesionalRequest;
import com.hospital.gestion_de_citas.dto.response.ProfesionalResponse;
import com.hospital.gestion_de_citas.exception.ProfesionalNoEncontradoException;
import com.hospital.gestion_de_citas.mapper.ProfesionalMapper;
import com.hospital.gestion_de_citas.model.Profesional;
import com.hospital.gestion_de_citas.repository.ProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesionalService {
    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalMapper profesionalMapper;
    private static final Logger log = LoggerFactory.getLogger(ProfesionalService.class);

    //OBTENER TODOS LOS PROFESIONALES
    public List<ProfesionalResponse> listaProfesionales(){
        return profesionalMapper.toResponseList(profesionalRepository.findAll());
    }

    //BUSCAR PROFESIONAL POR ID
    public ProfesionalResponse buscarPorId(Long idBuscado){
        log.info("Se está obteniendo el profesional con el id {}", idBuscado);
        return profesionalMapper.toResponse(profesionalRepository.findById(idBuscado).orElseThrow(()-> new ProfesionalNoEncontradoException(idBuscado)));
    }

    //CREAR PROFESIONAL

    public ProfesionalResponse crear(ProfesionalRequest profesionalRequest){
        if(profesionalRepository.existsByRut(profesionalRequest.getRut())){
            throw new RuntimeException("Ya existe un profesional con el rut: "+ profesionalRequest.getRut());
        }

        if(profesionalRepository.existsByNumLicencia(profesionalRequest.getNumLicencia())){
            throw new RuntimeException("Ya existe un profesional con el número de licencia: "+profesionalRequest.getNumLicencia());
        }
        log.info("Datos del profesional al momento de crear {}", profesionalRequest);
        return profesionalMapper.toResponse(
                profesionalRepository.save(
                        profesionalMapper.toEntity(profesionalRequest)));
    }

    //ELIMINAR PROFESIONAL

    public void eliminar (Long idBuscado){
        profesionalRepository.deleteById(idBuscado);
    }

    //EDITAR PROFESIONAL

    public ProfesionalResponse actualizar(Long idBuscado, ProfesionalRequest profesionalRequest){
        Profesional profesionalEncontrado= profesionalRepository
                .findById(idBuscado)
                .orElseThrow(()-> new ProfesionalNoEncontradoException(idBuscado));
        profesionalEncontrado.setRut(profesionalRequest.getRut());
        profesionalEncontrado.setNombreProfesional(profesionalRequest.getNombreProfesional());
        profesionalEncontrado.setEspecialidad(profesionalRequest.getEspecialidad());
        profesionalEncontrado.setTelefono(profesionalRequest.getTelefono());
        profesionalEncontrado.setCorreo(profesionalRequest.getCorreo());
        profesionalEncontrado.setDireccion(profesionalRequest.getDireccion());
        profesionalEncontrado.setNumLicencia(profesionalRequest.getNumLicencia());
        profesionalEncontrado.setFechaContratacion(profesionalRequest.getFechaContratacion());

        log.info("Datos del profesional al momento de actualizar {}", profesionalRequest);
        return profesionalMapper.toResponse(profesionalRepository.save(profesionalEncontrado));
    }
}
