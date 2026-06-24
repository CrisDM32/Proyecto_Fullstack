package com.hospital.gestion_de_citas.mapper;

import com.hospital.gestion_de_citas.dto.request.ProfesionalRequest;
import com.hospital.gestion_de_citas.dto.response.ProfesionalResponse;
import com.hospital.gestion_de_citas.model.Profesional;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesionalMapper {
    Profesional toEntity(ProfesionalRequest profesionalRequest);
    ProfesionalResponse toResponse(Profesional profesional);
    List<ProfesionalResponse> toResponseList(List<Profesional> profesionalList);
}
