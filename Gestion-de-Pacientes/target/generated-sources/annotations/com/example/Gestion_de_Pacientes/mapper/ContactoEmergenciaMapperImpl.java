package com.example.Gestion_de_Pacientes.mapper;

import com.example.Gestion_de_Pacientes.dto.request.ContactoEmergenciaRequest;
import com.example.Gestion_de_Pacientes.dto.response.ContactoEmergenciaResponse;
import com.example.Gestion_de_Pacientes.model.ContactoEmergencia;
import com.example.Gestion_de_Pacientes.model.Paciente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-27T18:01:09-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Eclipse Adoptium)"
)
@Component
public class ContactoEmergenciaMapperImpl implements ContactoEmergenciaMapper {

    @Override
    public ContactoEmergencia toEntity(ContactoEmergenciaRequest contactoEmergenciaRequest) {
        if ( contactoEmergenciaRequest == null ) {
            return null;
        }

        ContactoEmergencia contactoEmergencia = new ContactoEmergencia();

        contactoEmergencia.setNombreContactoEmergencia( contactoEmergenciaRequest.getNombreContactoEmergencia() );
        contactoEmergencia.setTelefonoContactoEmergencia( contactoEmergenciaRequest.getTelefonoContactoEmergencia() );

        return contactoEmergencia;
    }

    @Override
    public ContactoEmergenciaResponse toResponse(ContactoEmergencia contactoEmergencia) {
        if ( contactoEmergencia == null ) {
            return null;
        }

        ContactoEmergenciaResponse contactoEmergenciaResponse = new ContactoEmergenciaResponse();

        contactoEmergenciaResponse.setNombrePaciente( contactoEmergenciaPacienteNombrePaciente( contactoEmergencia ) );
        contactoEmergenciaResponse.setIdPaciente( contactoEmergenciaPacienteIdPaciente( contactoEmergencia ) );
        contactoEmergenciaResponse.setIdContactoEmergencia( contactoEmergencia.getIdContactoEmergencia() );
        contactoEmergenciaResponse.setNombreContactoEmergencia( contactoEmergencia.getNombreContactoEmergencia() );
        contactoEmergenciaResponse.setTelefonoContactoEmergencia( contactoEmergencia.getTelefonoContactoEmergencia() );

        return contactoEmergenciaResponse;
    }

    @Override
    public List<ContactoEmergenciaResponse> toResponseList(List<ContactoEmergencia> contactoEmergenciaList) {
        if ( contactoEmergenciaList == null ) {
            return null;
        }

        List<ContactoEmergenciaResponse> list = new ArrayList<ContactoEmergenciaResponse>( contactoEmergenciaList.size() );
        for ( ContactoEmergencia contactoEmergencia : contactoEmergenciaList ) {
            list.add( toResponse( contactoEmergencia ) );
        }

        return list;
    }

    private String contactoEmergenciaPacienteNombrePaciente(ContactoEmergencia contactoEmergencia) {
        Paciente paciente = contactoEmergencia.getPaciente();
        if ( paciente == null ) {
            return null;
        }
        return paciente.getNombrePaciente();
    }

    private Long contactoEmergenciaPacienteIdPaciente(ContactoEmergencia contactoEmergencia) {
        Paciente paciente = contactoEmergencia.getPaciente();
        if ( paciente == null ) {
            return null;
        }
        return paciente.getIdPaciente();
    }
}
