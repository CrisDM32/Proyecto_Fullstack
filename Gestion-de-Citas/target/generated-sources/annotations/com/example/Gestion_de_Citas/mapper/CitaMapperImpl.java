package com.example.Gestion_de_Citas.mapper;

import com.example.Gestion_de_Citas.dto.request.CitaRequest;
import com.example.Gestion_de_Citas.dto.response.CitaResponse;
import com.example.Gestion_de_Citas.model.AgendaMedica;
import com.example.Gestion_de_Citas.model.Cita;
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
public class CitaMapperImpl implements CitaMapper {

    @Override
    public Cita toEntity(CitaRequest citaRequest) {
        if ( citaRequest == null ) {
            return null;
        }

        Cita cita = new Cita();

        cita.setIdPaciente( citaRequest.getIdPaciente() );
        cita.setIdProfesional( citaRequest.getIdProfesional() );
        cita.setIdPago( citaRequest.getIdPago() );
        cita.setFechaHoraCita( citaRequest.getFechaHoraCita() );
        cita.setMotivoConsulta( citaRequest.getMotivoConsulta() );
        cita.setEstadoCita( citaRequest.getEstadoCita() );

        return cita;
    }

    @Override
    public CitaResponse toResponse(Cita cita) {
        if ( cita == null ) {
            return null;
        }

        CitaResponse citaResponse = new CitaResponse();

        citaResponse.setIdAgenda( citaAgendaIdAgenda( cita ) );
        citaResponse.setIdCita( cita.getIdCita() );
        citaResponse.setIdPaciente( cita.getIdPaciente() );
        citaResponse.setIdProfesional( cita.getIdProfesional() );
        citaResponse.setIdPago( cita.getIdPago() );
        citaResponse.setFechaHoraCita( cita.getFechaHoraCita() );
        citaResponse.setMotivoConsulta( cita.getMotivoConsulta() );
        citaResponse.setEstadoCita( cita.getEstadoCita() );

        return citaResponse;
    }

    @Override
    public List<CitaResponse> toResponseList(List<Cita> citaList) {
        if ( citaList == null ) {
            return null;
        }

        List<CitaResponse> list = new ArrayList<CitaResponse>( citaList.size() );
        for ( Cita cita : citaList ) {
            list.add( toResponse( cita ) );
        }

        return list;
    }

    private Long citaAgendaIdAgenda(Cita cita) {
        AgendaMedica agenda = cita.getAgenda();
        if ( agenda == null ) {
            return null;
        }
        return agenda.getIdAgenda();
    }
}
