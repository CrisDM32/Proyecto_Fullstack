package com.example.Gestion_de_Pacientes.mapper;

import com.example.Gestion_de_Pacientes.dto.request.PacienteRequest;
import com.example.Gestion_de_Pacientes.dto.response.PacienteResponse;
import com.example.Gestion_de_Pacientes.model.ContactoEmergencia;
import com.example.Gestion_de_Pacientes.model.Paciente;
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
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public Paciente toEntity(PacienteRequest pacienteRequest) {
        if ( pacienteRequest == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        paciente.setNombrePaciente( pacienteRequest.getNombrePaciente() );
        paciente.setRut( pacienteRequest.getRut() );
        paciente.setFechaNacimiento( pacienteRequest.getFechaNacimiento() );
        paciente.setGenero( pacienteRequest.getGenero() );
        paciente.setTelefono( pacienteRequest.getTelefono() );
        paciente.setCorreo( pacienteRequest.getCorreo() );
        paciente.setDireccion( pacienteRequest.getDireccion() );
        paciente.setGrupoSanguineo( pacienteRequest.getGrupoSanguineo() );
        List<ContactoEmergencia> list = pacienteRequest.getContactoEmergencia();
        if ( list != null ) {
            paciente.setContactoEmergencia( new ArrayList<ContactoEmergencia>( list ) );
        }

        return paciente;
    }

    @Override
    public PacienteResponse toResponse(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteResponse pacienteResponse = new PacienteResponse();

        pacienteResponse.setIdPaciente( paciente.getIdPaciente() );
        pacienteResponse.setNombrePaciente( paciente.getNombrePaciente() );
        pacienteResponse.setRut( paciente.getRut() );
        pacienteResponse.setFechaNacimiento( paciente.getFechaNacimiento() );
        pacienteResponse.setGenero( paciente.getGenero() );
        pacienteResponse.setTelefono( paciente.getTelefono() );
        pacienteResponse.setCorreo( paciente.getCorreo() );
        pacienteResponse.setDireccion( paciente.getDireccion() );
        pacienteResponse.setGrupoSanguineo( paciente.getGrupoSanguineo() );
        List<ContactoEmergencia> list = paciente.getContactoEmergencia();
        if ( list != null ) {
            pacienteResponse.setContactoEmergencia( new ArrayList<ContactoEmergencia>( list ) );
        }

        return pacienteResponse;
    }

    @Override
    public List<PacienteResponse> toResponseList(List<Paciente> pacienteList) {
        if ( pacienteList == null ) {
            return null;
        }

        List<PacienteResponse> list = new ArrayList<PacienteResponse>( pacienteList.size() );
        for ( Paciente paciente : pacienteList ) {
            list.add( toResponse( paciente ) );
        }

        return list;
    }
}
