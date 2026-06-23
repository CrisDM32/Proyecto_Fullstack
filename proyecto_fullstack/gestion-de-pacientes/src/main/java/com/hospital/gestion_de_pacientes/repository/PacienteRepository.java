package com.hospital.gestion_de_pacientes.repository;

import com.hospital.gestion_de_pacientes.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    boolean existsByRut(int Rut);
}