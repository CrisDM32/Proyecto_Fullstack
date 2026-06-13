package com.hospital.Gestion_de_Citas.mapper;

import com.hospital.Gestion_de_Citas.dto.request.ProfesionalRequest;
import com.hospital.Gestion_de_Citas.dto.response.ProfesionalResponse;
import com.hospital.Gestion_de_Citas.model.Profesional;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesionalMapper {
    Profesional toEntity(ProfesionalRequest profesionalRequest);
    ProfesionalResponse toResponse(Profesional profesional);
    List<ProfesionalResponse> toResponseList(List<Profesional> profesionalList);
}
