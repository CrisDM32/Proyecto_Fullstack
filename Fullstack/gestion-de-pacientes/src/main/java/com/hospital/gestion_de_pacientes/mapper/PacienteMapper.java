package com.hospital.gestion_de_pacientes.mapper;

import com.hospital.gestion_de_pacientes.dto.request.PacienteRequest;
import com.hospital.gestion_de_pacientes.dto.response.PacienteResponse;
import com.hospital.gestion_de_pacientes.model.Paciente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    Paciente toEntity(PacienteRequest pacienteRequest);

    PacienteResponse toResponse(Paciente paciente);

    List<PacienteResponse> toResponseList(List<Paciente> pacienteList);
}

