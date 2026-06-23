package com.hospital.gestion_de_citas.mapper;

import com.hospital.gestion_de_citas.dto.request.CitaRequest;
import com.hospital.gestion_de_citas.dto.response.CitaResponse;
import com.hospital.gestion_de_citas.model.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {
    Cita toEntity(CitaRequest citaRequest);

    @Mapping(source = "agenda.idAgenda", target = "idAgenda")
    CitaResponse toResponse(Cita cita);

    List<CitaResponse> toResponseList(List<Cita> citaList);
}
