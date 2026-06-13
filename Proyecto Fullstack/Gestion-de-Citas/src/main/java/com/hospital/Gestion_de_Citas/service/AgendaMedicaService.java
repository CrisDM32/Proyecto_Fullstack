package com.hospital.Gestion_de_Citas.service;

import com.hospital.Gestion_de_Citas.dto.request.AgendaMedicaRequest;
import com.hospital.Gestion_de_Citas.dto.response.AgendaMedicaResponse;
import com.hospital.Gestion_de_Citas.exception.AgendaNoEncontradaException;
import com.hospital.Gestion_de_Citas.mapper.AgendaMedicaMapper;
import com.hospital.Gestion_de_Citas.model.AgendaMedica;
import com.hospital.Gestion_de_Citas.model.Profesional;
import com.hospital.Gestion_de_Citas.repository.AgendaMedicaRepository;
import com.hospital.Gestion_de_Citas.repository.CitaRepository;
import com.hospital.Gestion_de_Citas.repository.ProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaMedicaService {
    private final AgendaMedicaRepository agendaMedicaRepository;
    private final AgendaMedicaMapper agendaMedicaMapper;
    private final CitaRepository citaRepository;
    private final ProfesionalRepository profesionalRepository;

    //OBTENER TODAS LAS AGENDAS
    public List<AgendaMedicaResponse> listaAgendas(){
        return agendaMedicaMapper.toResponseList(agendaMedicaRepository.findAll());
    }

    //OBTENER AGENDA POR ID AGENDA
    public AgendaMedicaResponse obtenerPorID(Long idBuscado){
        return agendaMedicaMapper.toResponse(agendaMedicaRepository
                .findById(idBuscado)
                .orElseThrow(()-> new AgendaNoEncontradaException(idBuscado)));
    }

    //OBTENER AGENDA POR ID PROFESIONAL

    public List<AgendaMedicaResponse> obtenerPorIdProfesional(Long idProfesional){

        List<AgendaMedica> agendaMedicaList =
                agendaMedicaRepository.findByProfesionalIdProfesional(idProfesional);

        if(agendaMedicaList.isEmpty()){
            throw new RuntimeException("No existen Agendas Medicas para el profesional: "+ idProfesional);
        }

        for (AgendaMedica agenda : agendaMedicaList) {
            agenda.setCitas(
                    citaRepository.findByAgendaIdAgenda(agenda.getIdAgenda())
            );
        }

        return agendaMedicaMapper.toResponseList(agendaMedicaList);
    }

    //CREAR AGENDA MEDICA
    public AgendaMedicaResponse crear(AgendaMedicaRequest agendaMedicaRequest){

        AgendaMedica agenda = agendaMedicaMapper
                .toEntity(agendaMedicaRequest);


        Profesional profesional = profesionalRepository
                .findById(agendaMedicaRequest
                        .getIdProfesional()).orElseThrow(() -> new  RuntimeException ("Profesional no encontrado"));

        agenda.setProfesional(profesional);

        return agendaMedicaMapper
                .toResponse(
                        agendaMedicaRepository.save(agenda));
    }

    //ELIMINAR AGENDA MEDICA
    public void eliminar (Long idBuscado){
        agendaMedicaRepository.deleteById(idBuscado);
    }

    //EDITAR UNA AGENDA MEDICA
    public AgendaMedicaResponse actualizar(Long id, AgendaMedicaRequest agendaMedicaRequest) {

        AgendaMedica agendaExistente = agendaMedicaRepository
                .findById(id)
                .orElseThrow(() -> new AgendaNoEncontradaException(id));

        Profesional profesional = profesionalRepository
                .findById(agendaMedicaRequest.getIdProfesional())
                .orElseThrow(() -> new RuntimeException("Profesional no encontrado"));

        AgendaMedica agendaActualizada = agendaMedicaMapper
                .toEntity(agendaMedicaRequest);

        agendaActualizada.setIdAgenda(agendaExistente.getIdAgenda());
        agendaActualizada.setFecha(agendaExistente.getFecha());
        agendaActualizada.setHoraInicioTurno(agendaExistente.getHoraInicioTurno());
        agendaActualizada.setHoraFinTurno(agendaExistente.getHoraFinTurno());
        agendaActualizada.setAsistenciaTurno(agendaExistente.getAsistenciaTurno());
        agendaActualizada.setProfesional(profesional);

        return agendaMedicaMapper.toResponse(agendaMedicaRepository.save(agendaActualizada));
    }
}
