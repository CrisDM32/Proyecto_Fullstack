package com.hospital.Gestion_de_Pacientes.mapper;

import com.hospital.Gestion_de_Pacientes.dto.request.PacienteRequest;
import com.hospital.Gestion_de_Pacientes.dto.response.PacienteResponse;
import com.hospital.Gestion_de_Pacientes.model.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente toEntity(PacienteRequest pacienteRequest);

    PacienteResponse toResponse(Paciente paciente);

    List<PacienteResponse> toResponseList(List<Paciente> pacienteList);
}

