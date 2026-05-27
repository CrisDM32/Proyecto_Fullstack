package com.example.Pagos.mapper;

import com.example.Pagos.dto.request.CitaRequest;
import com.example.Pagos.dto.response.CitaResponse;
import com.example.Pagos.model.Cita;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {PagoMapper.class}
)
public interface CitaMapper {

    Cita toEntity(CitaRequest citaRequest);

    CitaResponse toResponse(Cita cita);

    List<CitaResponse> toResponseList(List<Cita> citaList);
}