package com.hospital.pagos.mapper;

import com.hospital.pagos.dto.request.CitaRequest;
import com.hospital.pagos.dto.response.CitaResponse;
import com.hospital.pagos.model.Cita;
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