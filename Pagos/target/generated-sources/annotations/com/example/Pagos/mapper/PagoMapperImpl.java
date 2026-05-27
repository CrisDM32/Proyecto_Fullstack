package com.example.Pagos.mapper;

import com.example.Pagos.dto.request.PagoRequest;
import com.example.Pagos.dto.response.PagoResponse;
import com.example.Pagos.model.Cita;
import com.example.Pagos.model.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-27T17:57:26-0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Eclipse Adoptium)"
)
@Component
public class PagoMapperImpl implements PagoMapper {

    @Override
    public Pago toEntity(PagoRequest pagoRequest) {
        if ( pagoRequest == null ) {
            return null;
        }

        Pago pago = new Pago();

        pago.setMontoPago( pagoRequest.getMontoPago() );
        pago.setMetodoPago( pagoRequest.getMetodoPago() );
        pago.setIdTransaccionTarjeta( pagoRequest.getIdTransaccionTarjeta() );
        pago.setEstadoPago( pagoRequest.getEstadoPago() );
        pago.setFechaPago( pagoRequest.getFechaPago() );

        return pago;
    }

    @Override
    public PagoResponse toResponse(Pago pago) {
        if ( pago == null ) {
            return null;
        }

        PagoResponse pagoResponse = new PagoResponse();

        pagoResponse.setIdCita( pagoCitaIdCita( pago ) );
        pagoResponse.setIdPago( pago.getIdPago() );
        pagoResponse.setMontoPago( pago.getMontoPago() );
        pagoResponse.setMetodoPago( pago.getMetodoPago() );
        pagoResponse.setIdTransaccionTarjeta( pago.getIdTransaccionTarjeta() );
        pagoResponse.setEstadoPago( pago.getEstadoPago() );
        pagoResponse.setFechaPago( pago.getFechaPago() );

        return pagoResponse;
    }

    @Override
    public List<PagoResponse> toResponseList(List<Pago> pagoList) {
        if ( pagoList == null ) {
            return null;
        }

        List<PagoResponse> list = new ArrayList<PagoResponse>( pagoList.size() );
        for ( Pago pago : pagoList ) {
            list.add( toResponse( pago ) );
        }

        return list;
    }

    private Long pagoCitaIdCita(Pago pago) {
        Cita cita = pago.getCita();
        if ( cita == null ) {
            return null;
        }
        return cita.getIdCita();
    }
}
