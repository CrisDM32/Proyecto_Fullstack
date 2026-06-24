package com.hospital.gestion_de_pacientes.services;

import com.hospital.gestion_de_pacientes.dto.request.ContactoEmergenciaRequest;
import com.hospital.gestion_de_pacientes.dto.response.ContactoEmergenciaResponse;
import com.hospital.gestion_de_pacientes.exception.ContactoNoEncontradoException;
import com.hospital.gestion_de_pacientes.mapper.ContactoEmergenciaMapper;
import com.hospital.gestion_de_pacientes.model.ContactoEmergencia;
import com.hospital.gestion_de_pacientes.model.Paciente;
import com.hospital.gestion_de_pacientes.repository.ContactoEmergenciaRepository;
import com.hospital.gestion_de_pacientes.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoEmergenciaService {
    private final ContactoEmergenciaRepository contactoEmergenciaRepository;
    private final ContactoEmergenciaMapper contactoEmergenciaMapper;
    private final PacienteRepository pacienteRepository;
    private static final Logger log = LoggerFactory.getLogger(ContactoEmergenciaService.class);

    //mostrar todos los contactos

    public List<ContactoEmergenciaResponse> obtenerContactos(){
        return contactoEmergenciaMapper.toResponseList(contactoEmergenciaRepository.findAll());
    }

    //mostrar contacto por id

    public ContactoEmergenciaResponse obtenerPorId(Long id){
        log.info("Se está obteniendo el contacto de emergencia con el id {}", id);
        return contactoEmergenciaMapper.toResponse(contactoEmergenciaRepository.findById(id).orElseThrow(() -> new ContactoNoEncontradoException(id)));
    }

    //crear contacto
    public ContactoEmergenciaResponse crearContacto (ContactoEmergenciaRequest contactoEmergenciaRequest){
        ContactoEmergencia contactoEmergencia= contactoEmergenciaMapper.toEntity(contactoEmergenciaRequest);

        Paciente paciente = pacienteRepository.findById(contactoEmergenciaRequest.getIdPaciente()).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        contactoEmergencia.setPaciente(paciente);

        log.info("Datos del contacto de emergencia al momento de crear {}", contactoEmergenciaRequest);
        return contactoEmergenciaMapper.toResponse(contactoEmergenciaRepository.save(contactoEmergencia));
    }

    //eliminar contacto

    public void eliminarContacto(Long id){
        contactoEmergenciaRepository.deleteById(id);
    }

    //editar contacto
    public ContactoEmergenciaResponse actualizarContacto(Long id, ContactoEmergenciaRequest contactoEmergenciaRequest){
        ContactoEmergencia contactoEmergenciaExistente = contactoEmergenciaRepository.findById(id).orElseThrow(()->new ContactoNoEncontradoException(id));

        Paciente paciente = pacienteRepository.findById(contactoEmergenciaRequest.getIdPaciente()).orElseThrow(()->new RuntimeException("Paciente no encontrado."));

        ContactoEmergencia contactoEmergenciaActualizado = contactoEmergenciaMapper.toEntity(contactoEmergenciaRequest);

        contactoEmergenciaActualizado.setIdContactoEmergencia(contactoEmergenciaExistente.getIdContactoEmergencia());
        contactoEmergenciaActualizado.setPaciente(paciente);

        log.info("Datos del paciente al momento de actualizar {}", contactoEmergenciaRequest);
        return contactoEmergenciaMapper.toResponse(contactoEmergenciaRepository.save(contactoEmergenciaActualizado));

    }
}
