package com.hospital.Gestion_de_Pacientes.mapper;

import com.hospital.Gestion_de_Pacientes.dto.request.ContactoEmergenciaRequest;
import com.hospital.Gestion_de_Pacientes.dto.response.ContactoEmergenciaResponse;
import com.hospital.Gestion_de_Pacientes.model.ContactoEmergencia;
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