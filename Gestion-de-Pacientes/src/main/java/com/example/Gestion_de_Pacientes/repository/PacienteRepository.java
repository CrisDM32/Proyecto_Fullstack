package com.example.Gestion_de_Pacientes.repository;


import com.example.Gestion_de_Pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository <Paciente,Long> {
    boolean existeRut(int Rut);
}
