package com.example.Gestion_de_Citas.service;

import com.example.Gestion_de_Citas.client.PacientesClient;
import com.example.Gestion_de_Citas.dto.external.PacienteResponse;
import com.example.Gestion_de_Citas.dto.request.CitaRequest;
import com.example.Gestion_de_Citas.dto.response.CitaConPacienteResponse;
import com.example.Gestion_de_Citas.dto.response.CitaResponse;
import com.example.Gestion_de_Citas.exception.AgendaNoEncontradaException;
import com.example.Gestion_de_Citas.exception.CitaNoEncontradaException;
import com.example.Gestion_de_Citas.exception.ProfesionalNoEncontradoException;
import com.example.Gestion_de_Citas.mapper.CitaMapper;
import com.example.Gestion_de_Citas.model.AgendaMedica;
import com.example.Gestion_de_Citas.model.Cita;
import com.example.Gestion_de_Citas.model.EstadoCita;
import com.example.Gestion_de_Citas.model.Profesional;
import com.example.Gestion_de_Citas.repository.AgendaMedicaRepository;
import com.example.Gestion_de_Citas.repository.CitaRepository;
import com.example.Gestion_de_Citas.repository.ProfesionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CitaService {
    private final CitaRepository citaRepository;
    private final CitaMapper citaMapper;
    private final AgendaMedicaRepository agendaMedicaRepository;
    private final ProfesionalRepository profesionalRepository;
    private final PacientesClient pacientesClient;

    //obtener todas las citas

    public List<CitaResponse> listarCitas(){
        return citaMapper.toResponseList(citaRepository.findAll());
    }
    //obtener cita por id cita

    public CitaConPacienteResponse buscarPorIdCita(Long idCita){
        CitaResponse citaResponse = citaMapper.toResponse(citaRepository.findById(idCita).orElseThrow(()-> new CitaNoEncontradaException(idCita)));

        PacienteResponse pacienteResponse = pacientesClient.getPaciente(citaResponse.getIdPaciente());
        CitaConPacienteResponse citaConPacienteResponse = new CitaConPacienteResponse();

        citaConPacienteResponse.setIdCita(citaResponse.getIdCita());
        citaConPacienteResponse.setIdAgenda(citaResponse.getIdAgenda());
        citaConPacienteResponse.setIdProfesional(citaResponse.getIdProfesional());
        citaConPacienteResponse.setFechaHoraCita(citaResponse.getFechaHoraCita());
        citaConPacienteResponse.setMotivoConsulta(citaResponse.getMotivoConsulta());
        citaConPacienteResponse.setEstadoCita(citaResponse.getEstadoCita());
        citaConPacienteResponse.setPacienteResponse(pacienteResponse);

        return citaConPacienteResponse;
    }

    //obtener cita por id profesional
    public List<CitaResponse> buscarPorProfesional(Long idProfesional){
        List<Cita> citas = citaRepository.findByIdProfesional(idProfesional);

        if(citas.isEmpty()){
            throw new RuntimeException("No existen citas para el profesional con Id: "+ idProfesional);
        }

        return citaMapper.toResponseList(citas);
    }

    //obtener cita por id paciente
    public List<CitaResponse> buscarPorPaciente(Long idPaciente){
        List<Cita> citas = citaRepository.findByIdPaciente(idPaciente);

        if(citas.isEmpty()){
            throw new RuntimeException("No existen citas para el paciente con Id: "+ idPaciente);
        }

        return citaMapper.toResponseList(citas);
    }

    //obtener cita por id pago
    public List<CitaResponse> buscarPorPago(Long idPago){
        List<Cita> citas = citaRepository.findByIdPago(idPago);

        if(citas.isEmpty()){
            throw new RuntimeException("No existen citas para el pago con Id: "+ idPago);
        }

        return citaMapper.toResponseList(citas);
    }

    //obtener cita por id Agenda

    public List<CitaResponse> buscarPorAgenda(Long idAgenda){
        List<Cita> citas = citaRepository.findByAgendaIdAgenda(idAgenda);

        if(citas.isEmpty()){
            throw new RuntimeException("No existen citas para la agenda con Id: "+ idAgenda);
        }

        return citaMapper.toResponseList(citas);
    }

    //crear cita

    public CitaResponse crearCita(CitaRequest citaRequest){

        AgendaMedica agendaMedicaExistente = agendaMedicaRepository
                .findById(citaRequest.getIdAgenda())
                .orElseThrow(() -> new AgendaNoEncontradaException(citaRequest.getIdAgenda()));

        Profesional profesionalExistente = profesionalRepository
                .findById(citaRequest.getIdProfesional())
                .orElseThrow(() -> new ProfesionalNoEncontradoException(citaRequest.getIdProfesional()));

        LocalDateTime horarioSolicitado = citaRequest.getFechaHoraCita();

        if (horarioSolicitado.isBefore(agendaMedicaExistente.getHoraInicioTurno())
                || horarioSolicitado.isAfter(agendaMedicaExistente.getHoraFinTurno())) {
            throw new RuntimeException("Horario fuera de agenda");
        }

        boolean horarioOcupado = citaRepository
                .existsByFechaHoraCitaAndIdProfesional(
                        horarioSolicitado,
                        profesionalExistente.getIdProfesional()
                );

        if (horarioOcupado) {
            throw new RuntimeException("Horario ocupado");
        }

        Cita cita = new Cita();

        cita.setIdPaciente(citaRequest.getIdPaciente());
        cita.setIdProfesional(citaRequest.getIdProfesional());
        cita.setIdPago(citaRequest.getIdPago());
        cita.setFechaHoraCita(citaRequest.getFechaHoraCita());
        cita.setMotivoConsulta(citaRequest.getMotivoConsulta());
        cita.setEstadoCita(citaRequest.getEstadoCita());

        cita.setAgenda(agendaMedicaExistente);

        return citaMapper.toResponse(citaRepository.save(cita));
    }

    //cancelar cita

    public CitaResponse cancelarCita(Long id){
        Cita cita = citaRepository.findById(id).orElseThrow(()-> new CitaNoEncontradaException(id));
        cita.setEstadoCita(EstadoCita.CANCELADA);

        return citaMapper.toResponse(citaRepository.save(cita));
    }

    //eliminar cita

    public void eliminarCita(Long id){
        citaRepository.deleteById(id);
    }

    //editar cita

    public CitaResponse actualizarCita(Long id, CitaRequest citaRequest){
        Cita citaEncontrada = citaRepository.findById(id).orElseThrow(()-> new CitaNoEncontradaException(id));

        citaEncontrada.setIdPaciente(citaRequest.getIdPaciente());
        citaEncontrada.setIdProfesional(citaRequest.getIdProfesional());
        citaEncontrada.setIdPago(citaRequest.getIdPago());
        citaEncontrada.setFechaHoraCita(citaRequest.getFechaHoraCita());
        citaEncontrada.setMotivoConsulta(citaRequest.getMotivoConsulta());
        citaEncontrada.setEstadoCita(citaRequest.getEstadoCita());

        return citaMapper.toResponse(citaRepository.save(citaEncontrada));
    }
}
