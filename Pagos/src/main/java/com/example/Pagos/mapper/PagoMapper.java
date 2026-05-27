package com.example.Pagos.mapper;

import com.example.Pagos.dto.request.PagoRequest;
import com.example.Pagos.dto.response.PagoResponse;
import com.example.Pagos.model.Pago;
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
