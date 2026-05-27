package com.example.Gestion_de_Citas.mapper;

import com.example.Gestion_de_Citas.dto.request.AgendaMedicaRequest;
import com.example.Gestion_de_Citas.dto.response.AgendaMedicaResponse;
import com.example.Gestion_de_Citas.dto.response.CitaResponse;
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
    date = "2026-05-27T18:01:10-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Eclipse Adoptium)"
)
@Component
public class AgendaMedicaMapperImpl implements AgendaMedicaMapper {

    @Override
    public AgendaMedica toEntity(AgendaMedicaRequest agendaMedicaRequest) {
        if ( agendaMedicaRequest == null ) {
            return null;
        }

        AgendaMedica agendaMedica = new AgendaMedica();

        agendaMedica.setFecha( agendaMedicaRequest.getFecha() );
        agendaMedica.setHoraInicioTurno( agendaMedicaRequest.getHoraInicioTurno() );
        agendaMedica.setHoraFinTurno( agendaMedicaRequest.getHoraFinTurno() );
        agendaMedica.setAsistenciaTurno( agendaMedicaRequest.getAsistenciaTurno() );

        return agendaMedica;
    }

    @Override
    public AgendaMedicaResponse toResponse(AgendaMedica agendaMedica) {
        if ( agendaMedica == null ) {
            return null;
        }

        AgendaMedicaResponse agendaMedicaResponse = new AgendaMedicaResponse();

        agendaMedicaResponse.setIdProfesional( agendaMedicaProfesionalIdProfesional( agendaMedica ) );
        agendaMedicaResponse.setNombreProfesional( agendaMedicaProfesionalNombreProfesional( agendaMedica ) );
        agendaMedicaResponse.setCitas( citaListToCitaResponseList( agendaMedica.getCitas() ) );
        agendaMedicaResponse.setIdAgenda( agendaMedica.getIdAgenda() );
        agendaMedicaResponse.setFecha( agendaMedica.getFecha() );
        if ( agendaMedica.getHoraInicioTurno() != null ) {
            agendaMedicaResponse.setHoraInicioTurno( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( agendaMedica.getHoraInicioTurno() ) );
        }
        if ( agendaMedica.getHoraFinTurno() != null ) {
            agendaMedicaResponse.setHoraFinTurno( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( agendaMedica.getHoraFinTurno() ) );
        }
        agendaMedicaResponse.setAsistenciaTurno( agendaMedica.getAsistenciaTurno() );

        return agendaMedicaResponse;
    }

    @Override
    public List<AgendaMedicaResponse> toResponseList(List<AgendaMedica> agendaMedicaList) {
        if ( agendaMedicaList == null ) {
            return null;
        }

        List<AgendaMedicaResponse> list = new ArrayList<AgendaMedicaResponse>( agendaMedicaList.size() );
        for ( AgendaMedica agendaMedica : agendaMedicaList ) {
            list.add( toResponse( agendaMedica ) );
        }

        return list;
    }

    private Long agendaMedicaProfesionalIdProfesional(AgendaMedica agendaMedica) {
        Profesional profesional = agendaMedica.getProfesional();
        if ( profesional == null ) {
            return null;
        }
        return profesional.getIdProfesional();
    }

    private String agendaMedicaProfesionalNombreProfesional(AgendaMedica agendaMedica) {
        Profesional profesional = agendaMedica.getProfesional();
        if ( profesional == null ) {
            return null;
        }
        return profesional.getNombreProfesional();
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
}
