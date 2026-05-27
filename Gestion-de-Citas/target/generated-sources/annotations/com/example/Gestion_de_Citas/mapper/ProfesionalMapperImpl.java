package com.example.Gestion_de_Citas.mapper;

import com.example.Gestion_de_Citas.dto.request.ProfesionalRequest;
import com.example.Gestion_de_Citas.dto.response.AgendaMedicaResponse;
import com.example.Gestion_de_Citas.dto.response.CitaResponse;
import com.example.Gestion_de_Citas.dto.response.ProfesionalResponse;
import com.example.Gestion_de_Citas.model.AgendaMedica;
import com.example.Gestion_de_Citas.model.Cita;
import com.example.Gestion_de_Citas.model.Profesional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-27T18:01:11-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Eclipse Adoptium)"
)
@Component
public class ProfesionalMapperImpl implements ProfesionalMapper {

    @Override
    public Profesional toEntity(ProfesionalRequest profesionalRequest) {
        if ( profesionalRequest == null ) {
            return null;
        }

        Profesional profesional = new Profesional();

        profesional.setRut( profesionalRequest.getRut() );
        profesional.setNombreProfesional( profesionalRequest.getNombreProfesional() );
        profesional.setEspecialidad( profesionalRequest.getEspecialidad() );
        profesional.setTelefono( profesionalRequest.getTelefono() );
        profesional.setCorreo( profesionalRequest.getCorreo() );
        profesional.setDireccion( profesionalRequest.getDireccion() );
        profesional.setNumLicencia( profesionalRequest.getNumLicencia() );
        profesional.setFechaContratacion( profesionalRequest.getFechaContratacion() );

        return profesional;
    }

    @Override
    public ProfesionalResponse toResponse(Profesional profesional) {
        if ( profesional == null ) {
            return null;
        }

        ProfesionalResponse profesionalResponse = new ProfesionalResponse();

        profesionalResponse.setIdProfesional( profesional.getIdProfesional() );
        profesionalResponse.setRut( profesional.getRut() );
        profesionalResponse.setNombreProfesional( profesional.getNombreProfesional() );
        profesionalResponse.setEspecialidad( profesional.getEspecialidad() );
        profesionalResponse.setTelefono( profesional.getTelefono() );
        profesionalResponse.setCorreo( profesional.getCorreo() );
        profesionalResponse.setDireccion( profesional.getDireccion() );
        profesionalResponse.setNumLicencia( profesional.getNumLicencia() );
        profesionalResponse.setFechaContratacion( profesional.getFechaContratacion() );
        profesionalResponse.setAgendasMedicas( agendaMedicaListToAgendaMedicaResponseList( profesional.getAgendasMedicas() ) );

        return profesionalResponse;
    }

    @Override
    public List<ProfesionalResponse> toResponseList(List<Profesional> profesionalList) {
        if ( profesionalList == null ) {
            return null;
        }

        List<ProfesionalResponse> list = new ArrayList<ProfesionalResponse>( profesionalList.size() );
        for ( Profesional profesional : profesionalList ) {
            list.add( toResponse( profesional ) );
        }

        return list;
    }

    protected CitaResponse citaToCitaResponse(Cita cita) {
        if ( cita == null ) {
            return null;
        }

        CitaResponse citaResponse = new CitaResponse();

        citaResponse.setIdCita( cita.getIdCita() );
        citaResponse.setIdPaciente( cita.getIdPaciente() );
        citaResponse.setIdProfesional( cita.getIdProfesional() );
        citaResponse.setIdPago( cita.getIdPago() );
        citaResponse.setFechaHoraCita( cita.getFechaHoraCita() );
        citaResponse.setMotivoConsulta( cita.getMotivoConsulta() );
        citaResponse.setEstadoCita( cita.getEstadoCita() );

        return citaResponse;
    }

    protected List<CitaResponse> citaListToCitaResponseList(List<Cita> list) {
        if ( list == null ) {
            return null;
        }

        List<CitaResponse> list1 = new ArrayList<CitaResponse>( list.size() );
        for ( Cita cita : list ) {
            list1.add( citaToCitaResponse( cita ) );
        }

        return list1;
    }

    protected AgendaMedicaResponse agendaMedicaToAgendaMedicaResponse(AgendaMedica agendaMedica) {
        if ( agendaMedica == null ) {
            return null;
        }

        AgendaMedicaResponse agendaMedicaResponse = new AgendaMedicaResponse();

        agendaMedicaResponse.setIdAgenda( agendaMedica.getIdAgenda() );
        agendaMedicaResponse.setFecha( agendaMedica.getFecha() );
        if ( agendaMedica.getHoraInicioTurno() != null ) {
            agendaMedicaResponse.setHoraInicioTurno( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( agendaMedica.getHoraInicioTurno() ) );
        }
        if ( agendaMedica.getHoraFinTurno() != null ) {
            agendaMedicaResponse.setHoraFinTurno( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( agendaMedica.getHoraFinTurno() ) );
        }
        agendaMedicaResponse.setAsistenciaTurno( agendaMedica.getAsistenciaTurno() );
        agendaMedicaResponse.setCitas( citaListToCitaResponseList( agendaMedica.getCitas() ) );

        return agendaMedicaResponse;
    }

    protected List<AgendaMedicaResponse> agendaMedicaListToAgendaMedicaResponseList(List<AgendaMedica> list) {
        if ( list == null ) {
            return null;
        }

        List<AgendaMedicaResponse> list1 = new ArrayList<AgendaMedicaResponse>( list.size() );
        for ( AgendaMedica agendaMedica : list ) {
            list1.add( agendaMedicaToAgendaMedicaResponse( agendaMedica ) );
        }

        return list1;
    }
}
