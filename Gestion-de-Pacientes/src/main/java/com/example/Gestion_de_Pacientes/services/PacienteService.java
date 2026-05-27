package com.example.Gestion_de_Pacientes.services;

import com.example.Gestion_de_Pacientes.dto.request.PacienteRequest;
import com.example.Gestion_de_Pacientes.dto.response.PacienteResponse;
import com.example.Gestion_de_Pacientes.exception.PacienteNoEncontradoException;
import com.example.Gestion_de_Pacientes.mapper.PacienteMapper;
import com.example.Gestion_de_Pacientes.model.ContactoEmergencia;
import com.example.Gestion_de_Pacientes.model.Paciente;
import com.example.Gestion_de_Pacientes.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    //OBTENER TODOS LOS PACIENTES
    public List<PacienteResponse> listaPacientes(){

        return pacienteMapper.toResponseList(pacienteRepository.findAll());
    }

    //BUSCAR PACIENTE POR ID

    public PacienteResponse buscarPorId(Long idBuscado){
        return pacienteMapper.toResponse(pacienteRepository
                .findById(idBuscado)
                .orElseThrow(() -> new PacienteNoEncontradoException(idBuscado)));
    }

    //CREAR PACIENTE

    public PacienteResponse crear(PacienteRequest pacienteRequest){
        if (pacienteRepository.existeRut(pacienteRequest.getRut())){
            throw new RuntimeException("Ya existe un paciente registrado con el rut: "+ pacienteRequest.getRut());
        }
        return pacienteMapper.toResponse(pacienteRepository.save(pacienteMapper.toEntity(pacienteRequest)));
    }


    //ELIMINAR PACIENTE

    public void eliminar(Long idBuscado){
        pacienteRepository.deleteById(idBuscado);
    }

    //EDITAR PACIENTE

    public PacienteResponse actualizar(Long idBuscado, PacienteRequest pacienteRequest){
        Paciente pacienteEncontrado = pacienteRepository
                .findById(idBuscado)
                .orElseThrow(() -> new PacienteNoEncontradoException(idBuscado));

        pacienteEncontrado.setNombrePaciente(pacienteRequest.getNombrePaciente());
        pacienteEncontrado.setRut(pacienteRequest.getRut());
        pacienteEncontrado.setFechaNacimiento(pacienteRequest.getFechaNacimiento());
        pacienteEncontrado.setGenero(pacienteRequest.getGenero());
        pacienteEncontrado.setTelefono(pacienteRequest.getTelefono());
        pacienteEncontrado.setCorreo(pacienteRequest.getCorreo());
        pacienteEncontrado.setDireccion(pacienteRequest.getDireccion());
        pacienteEncontrado.setGrupoSanguineo(pacienteRequest.getGrupoSanguineo());
        pacienteEncontrado.setContactoEmergencia(pacienteRequest.getContactoEmergencia());



        return pacienteMapper.toResponse(pacienteRepository.save(pacienteEncontrado));


    }

}
