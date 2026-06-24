package com.hospital.pagos.mapper;

import com.hospital.pagos.dto.request.CitaRequest;
import com.hospital.pagos.dto.response.CitaResponse;
import com.hospital.pagos.model.Cita;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-24T17:59:15-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
public class CitaMapperImpl implements CitaMapper {

    @Autowired
    private PagoMapper pagoMapper;

    @Override
    public Cita toEntity(CitaRequest citaRequest) {
        if ( citaRequest == null ) {
            return null;
        }

        Cita cita = new Cita();

        cita.setNombrePaciente( citaRequest.getNombrePaciente() );

        return cita;
    }

    @Override
    public CitaResponse toResponse(Cita cita) {
        if ( cita == null ) {
            return null;
        }

        CitaResponse citaResponse = new CitaResponse();

        citaResponse.setIdCita( cita.getIdCita() );
        citaResponse.setNombrePaciente( cita.getNombrePaciente() );
        citaResponse.setPagos( pagoMapper.toResponseList( cita.getPagos() ) );

        return citaResponse;
    }

    @Override
    public List<CitaResponse> toResponseList(List<Cita> citaList) {
        if ( citaList == null ) {
            return null;
        }

        List<CitaResponse> list = new ArrayList<CitaResponse>( citaList.size() );
        for ( Cita cita : citaList ) {
            list.add( toResponse( cita ) );
        }

        return list;
    }
}
