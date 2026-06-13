package com.hospital.Gestion_de_Citas.mapper;

import com.hospital.Gestion_de_Citas.dto.request.AgendaMedicaRequest;
import com.hospital.Gestion_de_Citas.dto.response.AgendaMedicaResponse;
import com.hospital.Gestion_de_Citas.model.AgendaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgendaMedicaMapper {
    AgendaMedica toEntity(AgendaMedicaRequest agendaMedicaRequest);

    @Mapping(source = "profesional.idProfesional", target = "idProfesional")
    @Mapping(source = "profesional.nombreProfesional", target = "nombreProfesional")
    @Mapping(target = "citas", source = "citas")
    AgendaMedicaResponse toResponse(AgendaMedica agendaMedica);
    List<AgendaMedicaResponse> toResponseList(List<AgendaMedica> agendaMedicaList);
}