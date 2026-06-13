package com.hospital.Gestion_de_Citas.mapper;

import com.hospital.Gestion_de_Citas.dto.request.CitaRequest;
import com.hospital.Gestion_de_Citas.dto.response.CitaResponse;
import com.hospital.Gestion_de_Citas.model.Cita;
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
