package com.hospital.gestion_de_pacientes.mapper;

import com.hospital.gestion_de_pacientes.dto.request.ContactoEmergenciaRequest;
import com.hospital.gestion_de_pacientes.dto.response.ContactoEmergenciaResponse;
import com.hospital.gestion_de_pacientes.model.ContactoEmergencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactoEmergenciaMapper {
    ContactoEmergencia toEntity(ContactoEmergenciaRequest contactoEmergenciaRequest);

    @org.mapstruct.Mapping(source = "paciente.nombrePaciente", target = "nombrePaciente")
    @Mapping(source = "paciente.idPaciente", target = "idPaciente")
    ContactoEmergenciaResponse toResponse(ContactoEmergencia contactoEmergencia);
    List<ContactoEmergenciaResponse> toResponseList(List<ContactoEmergencia> contactoEmergenciaList);
}