package com.hospital.pagos.mapper;

import com.hospital.pagos.dto.request.PagoRequest;
import com.hospital.pagos.dto.response.PagoResponse;
import com.hospital.pagos.model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    Pago toEntity(PagoRequest pagoRequest);


    @Mapping(source = "cita.idCita", target = "idCita")
    PagoResponse toResponse(Pago pago);
    List<PagoResponse> toResponseList(List<Pago> pagoList);
}